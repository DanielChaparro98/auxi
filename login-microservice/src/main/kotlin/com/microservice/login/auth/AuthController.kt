package com.microservice.login.auth

import com.microservice.login.dto.RequestDto
import com.microservice.login.dto.UserDto
import com.microservice.login.model.Token
import com.microservice.login.model.User
import com.microservice.login.service.UserService
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController(@Autowired private val authService: UserService) {

    @PostMapping("/login")
    fun login(@RequestBody user: UserDto):ResponseEntity<Token>{
        val token:Token = authService.login(user)
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    fun validate(@RequestParam token:String,@RequestBody requestDto: RequestDto):ResponseEntity<Token>{
        val tokenDto:Token = authService.validate(token,requestDto)
        return ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/register")
    fun register(@RequestBody user: UserDto): ResponseEntity<User>{
        val userDto:User = authService.saveUser(user);
        return ResponseEntity.ok(userDto);
    }
}