package com.mall;

import com.mall.order.MallOrderApplication;
import com.mall.order.entity.OrderReturnReasonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallOrderApplication.class)
public class MallOrderApplicationTests {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);

    }

    @Test
    public void createQueue() {
        // Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        Queue queue = new Queue("hello-java-queue", true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void createBinding() {
        //(String destination, Binding.DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments)
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void sendMessage() {
        OrderReturnReasonEntity returnReasonEntity = new OrderReturnReasonEntity();
        returnReasonEntity.setId(12L);
        returnReasonEntity.setName("退回信息");
        returnReasonEntity.setSort(1);
        returnReasonEntity.setStatus(0);
        returnReasonEntity.setCreateTime(new Date());
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", returnReasonEntity);
    }


}
