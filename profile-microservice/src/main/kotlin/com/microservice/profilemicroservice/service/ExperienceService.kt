package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience

interface ExperienceService {
    fun save(experienceDto: ExperienceDto): Experience
    fun list(): List<Experience>

    fun listByEmail(email: String): List<Experience>
}