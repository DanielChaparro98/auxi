package com.microservice.profilemicroservice.controller

import com.microservice.profilemicroservice.dto.ProfileDto
import com.microservice.profilemicroservice.entity.Profile
import com.microservice.profilemicroservice.service.ProfileService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin("http://localhost:3000")
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

    @GetMapping("/listEmail")
    fun findByProfile(@RequestParam email:String):ResponseEntity<List<Profile>>{
        val listProfileEmail = profileService.findByEmail(email)
        return ResponseEntity.ok(listProfileEmail)
    }

}