package com.kurdestan.fooparking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class TestController {

    @GetMapping("/hi")
    public ResponseEntity<String> sayHi(){
        String hi = "hi kurdestan";
        return ResponseEntity.ok(hi);
    }

    @GetMapping("/hi/{name}")
    public ResponseEntity<String> sayHi(@PathVariable String name){
        String hi = "hi " + name;
        return ResponseEntity.ok(hi);
    }

    @GetMapping("/sum/{num1}/{num2}")
    public ResponseEntity<Integer> sayHi(@PathVariable int num1, @PathVariable int num2){
        Integer sum = num1 + num2;
        return ResponseEntity.ok(sum);
    }

}
