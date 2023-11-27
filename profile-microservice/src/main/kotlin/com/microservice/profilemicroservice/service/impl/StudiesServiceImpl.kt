package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.repository.StudiesRepository
import com.microservice.profilemicroservice.service.StudiesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudiesServiceImpl(@Autowired private val studiesRepository: StudiesRepository):StudiesService {
    override fun saveStudy(studiesDto: StudiesDto): Studies {
        val studyOptional =  studiesRepository.findByName(studiesDto.name)
        if(studyOptional.isPresent){
            throw Exception("Estudio ya existe")
        }
        var studies = Studies(name = studiesDto.name.lowercase() , dateGraduation = studiesDto.dateGraduation)
        return studiesRepository.save(studies)
    }

    override fun listStudies(): List<Studies> {
        return studiesRepository.findAll()
    }
}