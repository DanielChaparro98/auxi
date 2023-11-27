package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ProfileDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.entity.Profile
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.repository.ExperienceRepository
import com.microservice.profilemicroservice.repository.ProfileReposotory
import com.microservice.profilemicroservice.repository.StudiesRepository
import com.microservice.profilemicroservice.service.ExperienceService
import com.microservice.profilemicroservice.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(@Autowired private val profileReposotory: ProfileReposotory,
@Autowired private val experienceRepository: ExperienceRepository, @Autowired private val studiesRepository: StudiesRepository): ProfileService {
    override fun saveProfile(profileDto: ProfileDto): Profile {
        val profileOptional = profileReposotory.findByNumber(profileDto.number)
        if(profileOptional.isPresent){
            throw Exception("Experiencia ya existe")
        }
        var experience = mutableListOf<Experience>();
        for (index in profileDto.experiences){
            val experienceOptional = experienceRepository.findByName(index)
            if(!experienceOptional.isPresent){
                throw Exception("Experiencia no existe")
            }
            experience.addAll(listOf(experienceOptional.get()))
        }
        var studies = mutableListOf<Studies>()
        for(index in profileDto.studies){
            val studiesOptional =  studiesRepository.findByName(index)
            if(!studiesOptional.isPresent){
                throw Exception("Experiencia no existe")
            }
            studies.addAll(listOf(studiesOptional.get()))
        }
        var profile = Profile(number = profileDto.number, name = profileDto.name, studyType = profileDto.studyType,
            zone = profileDto.zone, schedule = profileDto.schedule, phone = profileDto.phone, experiences = experience,
            studies = studies)
        return profileReposotory.save(profile)
    }

    override fun listProfile(): List<Profile> {
        return profileReposotory.findAll()
    }
}