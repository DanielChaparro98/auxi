package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileReposotory: JpaRepository<Profile,Long> {
}