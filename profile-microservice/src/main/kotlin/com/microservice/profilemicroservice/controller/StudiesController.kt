package com.microservice.profilemicroservice.controller

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.service.StudiesService
import com.netflix.discovery.converters.Auto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/studies")
class StudiesController(@Autowired private val studiesService: StudiesService) {

    @PostMapping("/save")
    fun saveStudies(@RequestBody studiesDto: StudiesDto): ResponseEntity<Studies>{
        val studies = studiesService.saveStudy(studiesDto)
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