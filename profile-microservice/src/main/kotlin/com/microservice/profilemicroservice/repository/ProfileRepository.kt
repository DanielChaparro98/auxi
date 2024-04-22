package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileRepository: JpaRepository<Profile,Long> {

    fun findByName(name: String): Optional<Profile>
    fun findByEmail(email:String):Optional<Profile>
}