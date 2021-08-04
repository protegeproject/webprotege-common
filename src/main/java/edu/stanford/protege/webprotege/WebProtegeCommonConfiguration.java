package edu.stanford.protege.webprotege;

import edu.stanford.protege.webprotege.msg.MessageChannelMapper;
import edu.stanford.protege.webprotege.msg.ReplyingKafkaTemplateFactory;
import edu.stanford.protege.webprotege.msg.ReplyingKafkaTemplateFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
@SpringBootApplication
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
}
