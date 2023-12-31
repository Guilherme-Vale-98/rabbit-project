package com.gui.movietheaterproducer.controllers;

import com.gui.movietheaterproducer.services.RabbitMQService;
import constants.RabbitMQConstants;
import dtos.TheaterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "theaters")
public class TheaterController {
    @Autowired
    RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity putTheater(@RequestBody TheaterDto theaterDto){
        rabbitMQService.sendMessage(RabbitMQConstants.THEATER_QUEUE, theaterDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
