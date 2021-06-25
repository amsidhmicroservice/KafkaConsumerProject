package com.amsidh.mvc.kafkaconsumerproject.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

// To Consume the message from Kafka we need to create two bean ConsumerFactory and enable Kafka
@Configuration
@EnableKafka
public class AppConfig {

    @Bean
    public ConsumerFactory<Integer, String> getConsumerFactory() {
        Map<String, Object> consumerFactoryConfigMap = new HashMap<>();
        consumerFactoryConfigMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        consumerFactoryConfigMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        consumerFactoryConfigMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerFactoryConfigMap.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroupId");
        return new DefaultKafkaConsumerFactory(consumerFactoryConfigMap, new IntegerDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, String> getConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> concurrentKafkaListenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(getConsumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }

}
