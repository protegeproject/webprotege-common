package edu.stanford.protege.webprotege.msg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.kafka.support.KafkaHeaders.REPLY_TOPIC;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-03
 */
public class CommandExecutor<Q extends Request<R>, R extends Response> {


    private ReplyingKafkaTemplate<String, String, String> template = null;

    @Autowired
    private ReplyingKafkaTemplateFactory templateFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageChannelMapper channelMapper;

    private final Class<R> responseClass;

    public CommandExecutor(Class<R> responseClass) {
        this.responseClass = responseClass;
    }

    public CompletableFuture<R> execute(Q request) throws IOException, ExecutionException, InterruptedException {
        var replyTopic = channelMapper.getReplyChannelName(request);
        ensureFactory(replyTopic);
        var json = objectMapper.writeValueAsString(request);
        var topic = channelMapper.getChannelName(request);
        var msg = MessageBuilder.withPayload(json)
                                .setHeader(REPLY_TOPIC, replyTopic)
                                .setHeader(TOPIC, topic)
                                .build();
        var replyFuture = template.sendAndReceive(msg);
        // TODO:  Wrap Future
        return replyFuture.completable().thenApply(f -> {
            try {
                var replyJson = (String) f.getPayload();
                return objectMapper.readValue(replyJson, responseClass);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private synchronized void ensureFactory(String replyTopic) {
        if(template == null) {
            template = templateFactory.create(replyTopic);
            template.start();
        }
    }

}
