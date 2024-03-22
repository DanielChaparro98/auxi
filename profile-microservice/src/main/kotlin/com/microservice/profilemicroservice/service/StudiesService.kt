package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import org.springframework.web.multipart.MultipartFile

interface StudiesService {
    fun saveStudy(studiesDto: StudiesDto, file: MultipartFile):Studies
    fun findByStudy(id: Long): Studies
    fun listStudies():List<Studies>
    fun listFilter(email:String):List<Studies>
}