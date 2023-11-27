package com.microservice.profilemicroservice.controller

import com.microservice.profilemicroservice.dto.ProfileDto
import com.microservice.profilemicroservice.entity.Profile
import com.microservice.profilemicroservice.service.ProfileService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
class ProfileController(@Autowired private val profileService: ProfileService) {

    @PostMapping("/save")
    fun saveProfile(@RequestBody profileDto: ProfileDto):ResponseEntity<Profile>{
        val profile = profileService.saveProfile(profileDto)
        return ResponseEntity.ok(profile)
    }

    @GetMapping("/list")
    fun listProfile():ResponseEntity<List<Profile>>{
        val listProfile = profileService.listProfile()
        return ResponseEntity.ok(listProfile)
    }
}