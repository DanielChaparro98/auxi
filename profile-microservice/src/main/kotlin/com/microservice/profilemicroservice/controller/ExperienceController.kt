package com.microservice.profilemicroservice.controller

import com.google.gson.Gson
import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.service.ExperienceService
import lombok.RequiredArgsConstructor
import org.bouncycastle.oer.its.etsi102941.Url
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URLDecoder

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
class ExperienceController(@Autowired private val experienceService: ExperienceService) {

    @PostMapping("/save")
    fun saveExperience (@RequestParam("experience") experienceResponse: String, @RequestParam("certificate") certificate: MultipartFile):ResponseEntity<Experience>{
        val gson = Gson()
        val experienceDto: ExperienceDto = gson.fromJson(experienceResponse, ExperienceDto::class.java);
        val experience = experienceService.save(experienceDto,certificate);
        return ResponseEntity.ok(experience)
    }

    @GetMapping("/findExperience")
    fun findExperience(@RequestParam id:Long):ResponseEntity<Experience>{
        val experience = experienceService.findExperience(id)
        return ResponseEntity.ok(experience)
    }

    @GetMapping("/list")
    fun listExperience(): ResponseEntity<List<Experience>>{
        val listExperience:List<Experience> = experienceService.list();
        return ResponseEntity.ok(listExperience)
    }

    @GetMapping("/listFilter")
    fun listExperienceFilter(@RequestParam email:String): ResponseEntity<List<Long>>{
        val emailDecode = URLDecoder.decode(email,"UTF-8")
        val listExperienceFilter: List<Long> = experienceService.listByEmail(emailDecode)
        return ResponseEntity.ok(listExperienceFilter)
    }

    @GetMapping("/findByEmail")
    fun findByEmail(@RequestParam email: String): ResponseEntity<Experience>{
        val emailDecode = URLDecoder.decode(email,"UTF-8")
        val findEmail = experienceService.listByEmailObject(emailDecode)
        return ResponseEntity.ok(findEmail)
    }
}