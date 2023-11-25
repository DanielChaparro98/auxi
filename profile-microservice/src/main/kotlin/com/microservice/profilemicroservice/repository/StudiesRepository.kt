package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.Studies
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StudiesRepository:JpaRepository<Studies,Long> {
    fun findByName(name:String): Optional<Studies>
}