package com.microservice.transaction.Config;

import org.apache.kafka.clients.admin.*;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Configuration
public class KafkaStartUpValidator {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServer;
	
	private static final List<String> ReqTopics = List.of("NOTIFICATION-REQUEST");
	
	private final Logger log = LogManager.getLogger();
	
	@Bean
	public ApplicationRunner validateKafkaOnStartUp() {
		return args -> {
			Properties props = new Properties();
            props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
            
            try (AdminClient adminClient = AdminClient.create(props)) {

                // 🔹 1. Check Kafka reachable
                adminClient.describeCluster().nodes().get(3, TimeUnit.SECONDS);

                // 🔹 2. Fetch existing topics
                Set<String> existingTopics =
                        adminClient.listTopics().names().get(3, TimeUnit.SECONDS);

                // 🔹 3. Validate required topics
                for (String topic : ReqTopics) {
                    if (!existingTopics.contains(topic)) {
                        throw new RuntimeException("Missing required Kafka topic: " + topic);
                    }
                }

                log.info("✅ Kafka is reachable and all topics exist");

            } catch (Exception ex) {
               log.error("❌ Kafka startup validation failed: {}", ex.getMessage());

                // 🔥 VERY IMPORTANT → Fail application startup
                throw new IllegalStateException("Kafka is not ready", ex);
            }
		};
	}
	
}
