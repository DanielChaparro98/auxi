package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ProfileDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.entity.Profile
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.repository.ProfileRepository
import com.microservice.profilemicroservice.service.ExperienceService
import com.microservice.profilemicroservice.service.ProfileService
import com.microservice.profilemicroservice.service.StudiesService
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(@Autowired private val profileRepository: ProfileRepository,
                         @Autowired private val experienceService: ExperienceService, @Autowired private val studiesService: StudiesService): ProfileService {
    override fun saveProfile(profileDto: ProfileDto): Profile {
        val profileOptional = profileRepository.findByName(profileDto.name)
        if(profileOptional.isPresent){
            throw Exception("Perfil ya existe")
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
        return profileRepository.save(profile)
    }

    override fun updateProfile(id: Long, profileDto: ProfileDto): Profile {
        val profile =
            profileRepository.findById(id).orElseThrow { EntityNotFoundException("Experiencia no encontrada: $id") }

        profileDto.name?.let { profile.name = it }
        profileDto.phone?.let { profile.phone = it }
        profileDto.studyType?.let { profile.studyType = it }
        profileDto.beginSchedule?.let { profile.beginSchedule = it }
        profileDto.endSchedule?.let { profile.endSchedule = it }
        profileDto.zone?.let { profile.zone = it }
        profileDto.email?.let { profile.email = it }

        return profile
    }

    override fun listProfile(): List<Profile> {
        return profileRepository.findAll()
    }

    @Transactional
    override fun findByEmail(email: String): Profile? {
        val profile = profileRepository.findByEmail(email)
        return if (profile.isPresent) {
            profile.get()
        } else {
            null
        }
    }
}