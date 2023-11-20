package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExperienceRepository:JpaRepository<Experience,Long> {
    fun findByName(name:String): Optional<Experience>
}