package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.ProfileUserDto
import com.microservice.profilemicroservice.entity.ProfileUser
import org.springframework.web.multipart.MultipartFile

interface ProfileUserService {
    fun save(profileUserDto: ProfileUserDto, file:MultipartFile): ProfileUser
    fun update(id:Long, profileUserDto: ProfileUserDto, file: MultipartFile): ProfileUser
    fun listProfile():List<ProfileUser>
    fun findByEmail(email: String): ProfileUser?
}