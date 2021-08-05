package edu.stanford.protege.webprotege.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stanford.protege.webprotege.cmd.Request;
import edu.stanford.protege.webprotege.cmd.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-04
 */
public class HandlerWrapper<Q extends Request<R>, R extends Response> {

    private final ObjectMapper objectMapper;

    private final Class<Q> requestClass;

    private final CommandHandler<Q, R> commandHandler;

    public HandlerWrapper(CommandHandler<Q, R> commandHandler, Class<Q> requestClass, ObjectMapper objectMapper) {
        this.requestClass = requestClass;
        this.commandHandler = commandHandler;
        this.objectMapper = objectMapper;
    }

    // Add params at runtime
    @KafkaListener
    public String handleRequest(String request) {
        try {
            Q requestObject = objectMapper.readValue(request, requestClass);
            var responseObject = commandHandler.handleRequest(requestObject);
            return objectMapper.writeValueAsString(responseObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
