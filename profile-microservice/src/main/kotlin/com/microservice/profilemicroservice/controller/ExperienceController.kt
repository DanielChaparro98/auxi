package com.microservice.profilemicroservice.controller

import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.service.ExperienceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/experience")
class ExperienceController(@Autowired private val experienceService: ExperienceService) {

    @PostMapping("/save")
    fun saveExperience (@RequestBody experienceDto: ExperienceDto):ResponseEntity<Experience>{
        val experience = experienceService.save(experienceDto);
        return ResponseEntity.ok(experience)
    }

    @GetMapping("/list")
    fun listExperience(): ResponseEntity<List<Experience>>{
        val listExperience:List<Experience> = experienceService.list();
        return ResponseEntity.ok(listExperience)
    }

    @GetMapping("/listFilter")
    fun listExperienceFilter(@RequestParam email:String): ResponseEntity<List<Experience>>{
        val listExperienceFilter: List<Experience> = experienceService.listByEmail(email)
        return ResponseEntity.ok(listExperienceFilter)
    }
}