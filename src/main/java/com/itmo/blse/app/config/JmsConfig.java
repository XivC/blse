package com.itmo.blse.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


import com.rabbitmq.jms.admin.RMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.JMSException;


@Configuration
@EnableJms
public class JmsConfig {

    @Value("${rabbitmq.url}")
    private String connectionUrl;


    @Bean
    public RMQConnectionFactory connectionFactory() {
        RMQConnectionFactory factory = new RMQConnectionFactory();
        try {
            factory.setUri(connectionUrl);
        }
        catch (JMSException ex){
            throw new RuntimeException("Connection to broker failed");
        }
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestinationName("analytics.stats");
        return jmsTemplate;
    }
}