package com.microservice.profilemicroservice.controller

import com.google.gson.Gson
import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.dto.ProfileUserDto
import com.microservice.profilemicroservice.entity.ProfileUser
import com.microservice.profilemicroservice.service.ProfileUserService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@CrossOrigin("*")
@RestController
@RequestMapping("/profile/user")
@RequiredArgsConstructor
class ProfileUserController(@Autowired private val profileUserService: ProfileUserService) {

    @PostMapping("/save")
    fun saveProfileUser(
        @RequestParam("profileUser") profileUserDto: String,
        @RequestParam("medicalHistory") medicalHistory: MultipartFile
    ): ResponseEntity<ProfileUser> {
        val gson = Gson()
        val profileUserDtoParse = gson.fromJson(profileUserDto, ProfileUserDto::class.java)
        val profileUser = profileUserService.save(profileUserDtoParse, medicalHistory);
        return ResponseEntity.ok(profileUser)
    }

    @PostMapping("/update")
    fun updateProfileUser(
        @RequestParam id: Long,
        @RequestParam profileUserDto: String,
        @RequestParam("medicalHistory") medicalHistory: MultipartFile
    ): ResponseEntity<ProfileUser> {
        val gson = Gson()
        val profileUserParse: ProfileUserDto = gson.fromJson(profileUserDto, ProfileUserDto::class.java)
        val profileUser = profileUserService.update(id, profileUserParse, medicalHistory);
        return ResponseEntity.ok(profileUser)
    }

    @GetMapping("/list")
    fun listProfile():ResponseEntity<List<ProfileUser>>{
        var listProfile = profileUserService.listProfile()
        return ResponseEntity.ok(listProfile)
    }

    @GetMapping("/listEmail")
    fun findByEmail(@RequestParam email: String): ResponseEntity<ProfileUser>{
        var listProfileEmail = profileUserService.findByEmail(email)
        return ResponseEntity.ok(listProfileEmail)
    }
}