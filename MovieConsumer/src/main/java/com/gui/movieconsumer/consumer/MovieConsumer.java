package com.gui.movieconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.RabbitMQConstants;
import dtos.MovieDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MovieConsumer {
    @RabbitListener(queues = RabbitMQConstants.MOVIE_QUEUE)
    private void Consumer(String message) throws JsonProcessingException {
        MovieDto movieDto = new ObjectMapper().readValue(message, MovieDto.class);
        System.out.println(movieDto.title);
        System.out.println(movieDto.year);
        System.out.println("----------------------");
    }
}
