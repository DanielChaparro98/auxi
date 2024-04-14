package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.entity.Studies
import org.springframework.web.multipart.MultipartFile

interface ExperienceService {
    fun save(experienceDto: ExperienceDto, file: MultipartFile): Experience
    fun findExperience(id: Long): Experience
    fun list(): List<Experience>
    fun listByEmail(email: String): List<Long>
}