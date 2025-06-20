package com.kazie.kazie.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    @GetMapping("/tests")
    public ResponseEntity<String> welcomeKazie(){
        return ResponseEntity.ok("Bienvenue dans Kazie");
    }
}
