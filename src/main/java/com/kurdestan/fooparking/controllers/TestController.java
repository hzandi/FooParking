package com.kurdestan.fooparking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hi")
    public ResponseEntity<String> sayHi(){
        String hi = "hi kurdestan";
        return ResponseEntity.ok(hi);
    }

}
