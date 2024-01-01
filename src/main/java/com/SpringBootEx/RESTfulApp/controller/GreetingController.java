package com.SpringBootEx.RESTfulApp.controller;

import com.SpringBootEx.RESTfulApp.Greeting;
import com.SpringBootEx.RESTfulApp.datamodels.RequestData;
import com.SpringBootEx.RESTfulApp.datamodels.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    private final ObjectMapper objectMapper;

    public GreetingController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/greeting")
    public ResponseEntity<ResponseMessage> greeting(@RequestBody RequestData requestData) {
        ResponseMessage responseMessage;
        if (requestData.getResponse() == null) {
            responseMessage = new ResponseMessage("Please enter your response");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        String response = formatResponse(requestData.getResponse(), requestData.getFavoriteNumber());
        responseMessage = new ResponseMessage(response);
        return ResponseEntity.ok(responseMessage);
    }

    private String formatResponse(String response, int favoriteNumber) {
        return String.format("%s, my favorite number is %d", response, favoriteNumber);
    }
}
