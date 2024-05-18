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

        profileDto.name?.let {
            if (it.isNotEmpty()) {
                profile.name = it
            }
        }
        profileDto.phone?.let {
            if (it.isNotEmpty()) {
                profile.phone = it
            }
        }
        profileDto.studyType?.let {
            if (it.isNotEmpty()) {
                profile.studyType = it
            }
        }
        profileDto.beginSchedule?.let {
            if (it.isNotEmpty()) {
                profile.beginSchedule = it
            }
        }
        profileDto.endSchedule?.let {
            if (it.isNotEmpty()) {
                profile.endSchedule = it
            }
        }
        profileDto.zone?.let {
            if (it.isNotEmpty()) {
                profile.zone = it
            }
        }
        profileDto.email?.let {
            if (it.isNotEmpty()) {
                profile.email = it
            }
        }

        return profileRepository.save(profile)
    }

    override fun listProfile(): List<Profile> {
        return profileRepository.findAll()
    }

    @Transactional
    override fun findByEmail(email: String): Profile? {
        val profile = profileRepository.findByEmail(email)
        return if (profile.isPresent) {
            if(profile.get().studyType.equals("AUX")){
                profile.get().studyType = "Auxiliar de Enfermería"
            }else if (profile.get().studyType.equals("ENF")){
                profile.get().studyType = "Enfermería"
            }
            profile.get()
        } else {
            null
        }
    }
}