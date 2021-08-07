package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stanford.protege.webprotege.common.event.EventDispatcher;
import edu.stanford.protege.webprotege.common.cmd.MessageChannelMapper;
import edu.stanford.protege.webprotege.common.cmd.ReplyingKafkaTemplateFactory;
import edu.stanford.protege.webprotege.common.cmd.ReplyingKafkaTemplateFactoryImpl;
import edu.stanford.protege.webprotege.common.handler.KafkaListenerConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
@SpringBootApplication
@Import(KafkaListenerConfiguration.class)
public class WebProtegeCommonConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(WebProtegeCommonConfiguration.class, args);
    }


    @Bean
    MessageChannelMapper messageChannelMapper(@Value("${spring.application.name}") String serviceName) {
        return new MessageChannelMapper(serviceName);
    }

    @Bean
    ReplyingKafkaTemplateFactory replyingKafkaTemplateFactory(ConcurrentKafkaListenerContainerFactory<String, String> containerFactory,
                                                              ProducerFactory<String, String> producerFactory) {
        return new ReplyingKafkaTemplateFactoryImpl(containerFactory, producerFactory);
    }

    @Bean
    EventDispatcher eventDispatcher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        return new EventDispatcher(kafkaTemplate, objectMapper);
    }
}
