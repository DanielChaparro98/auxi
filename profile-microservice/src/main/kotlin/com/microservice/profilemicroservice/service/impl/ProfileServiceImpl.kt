package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ProfileDto
import com.microservice.profilemicroservice.entity.Profile
import com.microservice.profilemicroservice.repository.ProfileReposotory
import com.microservice.profilemicroservice.service.ProfileService
import org.springframework.beans.factory.annotation.Autowired

class ProfileServiceImpl(@Autowired private val profileReposotory: ProfileReposotory): ProfileService {
    override fun saveProfile(profileDto: ProfileDto): Profile {
        val profileOptional = profileReposotory.findByNumber(profileDto.number)
        if(profileOptional.isPresent){
            throw Exception("Experiencia ya existe")
        }
        var profile = Profile(number = profileDto.number, name = profileDto.name, studyType = profileDto.studyType,
            zone = profileDto.zone, schedule = profileDto.schedule, phone = profileDto.phone, experiences = profileDto.experiences,
            studies = profileDto.studies)
        return profileReposotory.save(profile)
    }

    override fun listProfile(): List<Profile> {
        return profileReposotory.findAll()
    }
}