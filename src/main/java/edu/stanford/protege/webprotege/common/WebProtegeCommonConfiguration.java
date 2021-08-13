package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

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
    @ConditionalOnMissingBean
    OWLDataFactory dataFactory() {
        return new OWLDataFactoryImpl();
    }
}
