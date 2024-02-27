package com.microservice.support.repository

import com.microservice.support.entity.Petition
import org.springframework.data.jpa.repository.JpaRepository

interface PetitionRepository:JpaRepository<Petition,Long> {
}