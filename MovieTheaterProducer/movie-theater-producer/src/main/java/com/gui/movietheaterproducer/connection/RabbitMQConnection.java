package com.gui.movietheaterproducer.connection;

import constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName){
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE,
                directExchange.getName(), queue.getName(),null);
    }
    @PostConstruct
    private void addQueue(){
        Queue movieQueue = this.queue(RabbitMQConstants.MOVIE_QUEUE);
        Queue theaterQueue = this.queue(RabbitMQConstants.THEATER_QUEUE);

        DirectExchange directExchange = this.directExchange();
        Binding movieBinding = this.binding(movieQueue, directExchange);
        Binding theaterBinding = this.binding(theaterQueue, directExchange);

        this.amqpAdmin.declareQueue(movieQueue);
        this.amqpAdmin.declareQueue(theaterQueue);

        this.amqpAdmin.declareExchange(directExchange);

        this.amqpAdmin.declareBinding(movieBinding);
        this.amqpAdmin.declareBinding(theaterBinding);
    }


}
