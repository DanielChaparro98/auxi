package com.microservice.profilemicroservice.service

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies

interface StudiesService {
    fun saveStudy(studiesDto: StudiesDto):Studies
    fun listStudies():List<Studies>
    fun listFilter(email:String):List<Studies>
}