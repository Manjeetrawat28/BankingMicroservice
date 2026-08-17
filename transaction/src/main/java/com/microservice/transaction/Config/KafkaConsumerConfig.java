package com.microservice.transaction.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import com.microservice.transaction.DTO.EndPointResponseDTO;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EndPointResponseDTO> kafkaListenerContainerFactory(
			ConsumerFactory<String, EndPointResponseDTO> consumerFactory){
		ConcurrentKafkaListenerContainerFactory<String, EndPointResponseDTO> factory = 
				new ConcurrentKafkaListenerContainerFactory<>();
		
		factory.setConsumerFactory(consumerFactory);
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		
		factory.setCommonErrorHandler(new DefaultErrorHandler(
                new FixedBackOff(2000L, 3) // retry 3 times with 2 sec gap
        ));

        return factory;
	
	}
	
}
