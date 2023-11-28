package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.repository.ExperienceRepository
import com.microservice.profilemicroservice.service.ExperienceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExperienceServiceImpl(@Autowired private val experienceRepository: ExperienceRepository):ExperienceService {
    override fun save(experienceDto: ExperienceDto): Experience {
        var experienceOptional =  experienceRepository.findByName(experienceDto.name)
        if(experienceOptional.isPresent){
            throw Exception("Experiencia ya existe")
        }
        var experience = Experience(name = experienceDto.name.lowercase(), date = experienceDto.date, typeContract = experienceDto.typeContract, emailUser = experienceDto.email)
        return experienceRepository.save(experience)
    }

    override fun list(): List<Experience> {
        return experienceRepository.findAll()
    }

    override fun listByEmail(email:String): List<Experience> {
        val experienceList = experienceRepository.findAll();
        val experienceListFilter = mutableListOf<Experience>()
        for (index in experienceList){

            if(index.emailUser == email){
                experienceListFilter.add(index)
            }
        }
        return experienceListFilter;
    }
}