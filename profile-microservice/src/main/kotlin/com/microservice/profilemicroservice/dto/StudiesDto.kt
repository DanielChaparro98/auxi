package com.microservice.profilemicroservice.dto

import com.microservice.profilemicroservice.entity.Profile
import jakarta.persistence.Lob
import java.util.Date

data class StudiesDto(
    val name:String,
    val type: String,
    val date: Date,
    val email: String,
)
