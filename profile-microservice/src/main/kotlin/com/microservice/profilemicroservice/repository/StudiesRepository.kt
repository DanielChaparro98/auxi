package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.Studies
import org.springframework.data.jpa.repository.JpaRepository

interface StudiesRepository:JpaRepository<Studies,Long> {
}