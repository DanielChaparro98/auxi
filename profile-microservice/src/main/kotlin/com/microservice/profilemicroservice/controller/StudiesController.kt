package com.microservice.profilemicroservice.controller

import com.google.gson.Gson
import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.service.StudiesService
import com.netflix.discovery.converters.Auto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/studies")
class StudiesController(@Autowired private val studiesService: StudiesService) {

    @PostMapping("/save")
    fun saveStudies(@RequestParam studiesResponse: String,@RequestParam file: MultipartFile): ResponseEntity<Studies>{
        val gson = Gson()
        val studiesDto: StudiesDto = gson.fromJson(studiesResponse,StudiesDto::class.java)
        val studies = studiesService.saveStudy(studiesDto, file)
        return ResponseEntity.ok(studies)
    }

    @GetMapping("/list")
    fun listStudies():ResponseEntity<List<Studies>>{
        val listStudies = studiesService.listStudies()
        return ResponseEntity.ok(listStudies)
    }

    @GetMapping("/listFilter")
    fun listFilter(@RequestParam email:String):ResponseEntity<List<Studies>>{
        val listStudies:List<Studies> = studiesService.listFilter(email)
        return ResponseEntity.ok(listStudies)
    }
}