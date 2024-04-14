package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import org.springframework.web.multipart.MultipartFile

interface StudiesService {
    fun saveStudy(studiesDto: StudiesDto, diploma: MultipartFile, rethus: MultipartFile, resolution: MultipartFile):Studies
    fun findByStudy(id: Long): Studies
    fun listStudies():List<Studies>
    fun findByEmail(email:String):List<Long>
}