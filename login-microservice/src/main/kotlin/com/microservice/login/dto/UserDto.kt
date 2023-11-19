package com.microservice.login.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
data class UserDto(

    val email:String,
    val password:String,
    val role: String
)
