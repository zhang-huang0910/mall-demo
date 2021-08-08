package com.mall.order.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @auther zhz
 * @Date 2020-11-22 21:02
 */
@Configuration
public class OrderConfig {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct//orderConfig对象创建完成后,执行此方法
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> System.out.println("confirm...correlationData[" + correlationData + "]==>ack" + b + "cause" + s));


        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

        });
    }

}
