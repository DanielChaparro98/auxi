package com.microservice.profilemicroservice.dto

import jakarta.persistence.Lob
import java.util.*


data class ExperienceDto(
        val name:String,
        val date : Date,
        val type: String,
        val email: String,
)
