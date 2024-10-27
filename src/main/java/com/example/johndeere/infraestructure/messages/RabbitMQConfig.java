package com.example.johndeere.infraestructure.messages;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String SESSIONS = "sessions";
    public static final String EVENTS = "events";
    public static final String EXCHANGE_NAME = "exchange";
    public static final String ROUTING_KEY_1 = "sessionsRoutingKey";
    public static final String ROUTING_KEY_2 = "eventsRoutingKey";

    @Bean
    public Queue queue1() {
        return new Queue(SESSIONS, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(EVENTS, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY_1);
    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY_2);
    }
}