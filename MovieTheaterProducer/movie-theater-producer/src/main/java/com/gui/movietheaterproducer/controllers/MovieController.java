package com.gui.movietheaterproducer.controllers;


import com.gui.movietheaterproducer.services.RabbitMQService;
import constants.RabbitMQConstants;
import dtos.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "movies")
public class MovieController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity editMovie(@RequestBody MovieDto movieDto){
        rabbitMQService.sendMessage(RabbitMQConstants.MOVIE_QUEUE, movieDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
