package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.ProfileDto
import com.microservice.profilemicroservice.entity.Profile

interface ProfileService {
    fun saveProfile(profileDto: ProfileDto):Profile
    fun updateProfile(id: Long, profileDto: ProfileDto): Profile
    fun listProfile():List<Profile>
    fun findByEmail(email:String):Profile?
}