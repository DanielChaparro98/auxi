package com.microservice.profilemicroservice.dto

import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.entity.Studies
import jakarta.persistence.OneToMany
import java.util.*

data class ProfileDto(
    val number: String,
    val name: String,
    val phone: String,
    val studyType: String,
    val schedule: Date,
    val zone: String,
    val experiences: List<Experience>,
    val studies: List<Studies>
)
