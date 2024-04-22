package com.microservice.profilemicroservice.controller

import com.google.gson.Gson
import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.service.StudiesService
import com.netflix.discovery.converters.Auto
import lombok.RequiredArgsConstructor
import org.apache.tomcat.util.buf.UriUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URLDecoder

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/studies")
@RequiredArgsConstructor
class StudiesController(@Autowired private val studiesService: StudiesService) {

    @PostMapping("/save")
    fun saveStudies(
        @RequestParam studiesResponse: String,
        @RequestParam diploma: MultipartFile,
        @RequestParam rethus: MultipartFile,
        @RequestParam resolution: MultipartFile
    ): ResponseEntity<Studies> {
        val gson = Gson()
        val studiesDto: StudiesDto = gson.fromJson(studiesResponse,StudiesDto::class.java)
        val studies = studiesService.saveStudy(studiesDto, diploma, rethus, resolution)
        return ResponseEntity.ok(studies)
    }

    @GetMapping("/list")
    fun listStudies():ResponseEntity<List<Studies>>{
        val listStudies = studiesService.listStudies()
        return ResponseEntity.ok(listStudies)
    }

    @GetMapping("/listFilter")
    fun listFilter(@RequestParam email:String):ResponseEntity<List<Long>>{
        val emailDecode = URLDecoder.decode(email,"UTF-8")
        val listStudies:List<Long> = studiesService.findByEmail(emailDecode)
        return ResponseEntity.ok(listStudies)
    }

    @GetMapping("/findByEmail")
    fun findByEmail(@RequestParam email: String): ResponseEntity<Studies>{
        val emailDecode = URLDecoder.decode(email,"UTF-8")
        val findByEmail = studiesService.listByEmailObject(emailDecode)
        return ResponseEntity.ok(findByEmail)
    }
}