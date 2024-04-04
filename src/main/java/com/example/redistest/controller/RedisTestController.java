package com.example.redistest.controller;

import com.example.redistest.controller.model.ResponseDTO;
import com.example.redistest.service.RedisTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class RedisTestController {
    private RedisTestService redisTestService;
    public RedisTestController() {
        this.redisTestService = RedisTestService.getINSTANCE();
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO> getBonitaet(@PathVariable(value = "id") String id) {
        int bonitaet = redisTestService.getBonitaet(id);
        ResponseDTO response = ResponseDTO.builder().bonitaet(bonitaet).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
