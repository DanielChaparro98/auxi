package com.microservice.login.auxi

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class AuxiController {

    @PostMapping("/auxi")
    fun home():String{
        return "Bienvenido";
    }
}