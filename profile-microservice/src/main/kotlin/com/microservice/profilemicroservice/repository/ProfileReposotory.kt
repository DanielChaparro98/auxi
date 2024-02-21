package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileReposotory: JpaRepository<Profile,Long> {

    fun findByNumber(number: String): Optional<Profile>
}