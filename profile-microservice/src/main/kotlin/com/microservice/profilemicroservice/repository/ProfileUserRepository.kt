package com.microservice.profilemicroservice.repository

import com.microservice.profilemicroservice.entity.ProfileUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProfileUserRepository: JpaRepository<ProfileUser,Long> {
    fun findByEmail(email:String):Optional<ProfileUser>
}