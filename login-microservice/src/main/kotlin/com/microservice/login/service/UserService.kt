package com.microservice.login.service

import com.microservice.login.dto.UserDto
import com.microservice.login.model.Token
import com.microservice.login.model.User

interface UserService {
    fun saveUser(user:UserDto):User
    fun login(user:UserDto):Token
    fun validate(token:String):Token
}