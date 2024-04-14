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
import com.microservice.profilemicroservice.service.StudiesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.math.exp

@Service
class ProfileServiceImpl(@Autowired private val profileReposotory: ProfileReposotory,
@Autowired private val experienceService: ExperienceService, @Autowired private val studiesService: StudiesService): ProfileService {
    override fun saveProfile(profileDto: ProfileDto): Profile {
        val profileOptional = profileReposotory.findByName(profileDto.name)
        if(profileOptional.isPresent){
            throw Exception("Experiencia ya existe")
        }
        val experienceEmail = experienceService.listByEmail(profileDto.email)
        val studiesEmail = studiesService.findByEmail(profileDto.email)
        var experience = mutableListOf<Experience>();
        for (index in experienceEmail){
            val experienceOptional = experienceService.findExperience(index)
            experience.addAll(listOf(experienceOptional))
        }
        var studies = mutableListOf<Studies>()
        for(index in studiesEmail){
            val studiesOptional =  studiesService.findByStudy(index)
            studies.addAll(listOf(studiesOptional))
        }
        var profile = Profile(
            name = profileDto.name,
            studyType = profileDto.studyType,
            zone = profileDto.zone,
            beginSchedule = profileDto.beginSchedule,
            endSchedule = profileDto.endSchedule,
            phone = profileDto.phone,
            email = profileDto.email,
            experiences = experience,
            studies = studies
        )
        return profileReposotory.save(profile)
    }

    override fun listProfile(): List<Profile> {
        return profileReposotory.findAll()
    }

    override fun findByEmail(email: String): List<Profile> {
        val profile = profileReposotory.findByEmail(email)
        return profile
    }
}