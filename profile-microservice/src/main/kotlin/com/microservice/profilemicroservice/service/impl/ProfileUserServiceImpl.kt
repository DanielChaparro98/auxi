package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ProfileUserDto
import com.microservice.profilemicroservice.entity.ProfileUser
import com.microservice.profilemicroservice.repository.ProfileUserRepository
import com.microservice.profilemicroservice.service.ProfileUserService
import com.microservice.profilemicroservice.util.File
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProfileUserServiceImpl(
    @Autowired private val profileUserRepository: ProfileUserRepository,
    @Autowired private val uploapFile: File
) : ProfileUserService {
    override fun save(profileUserDto: ProfileUserDto, file: MultipartFile): ProfileUser {
        val data = uploapFile.store(file)
        val profileUser = ProfileUser(
            name = profileUserDto.name,
            phone = profileUserDto.phone,
            address = profileUserDto.address,
            RH = profileUserDto.RH,
            medicalHistory = data,
            email = profileUserDto.email
        )
        return profileUserRepository.save(profileUser);
    }

    override fun update(id: Long, profileUserDto: ProfileUserDto, file:MultipartFile): ProfileUser {
        val profile =  profileUserRepository.findById(id).orElseThrow{ EntityNotFoundException("Perfil no encontrado: $id") }
        val data: ByteArray?;

        if(!file.isEmpty){
            data = uploapFile.store(file)
            profile.medicalHistory = data
        }

        profileUserDto.name?.let { profile.name = it }
        profileUserDto.phone?.let { profile.phone = it }
        profileUserDto.address?.let { profile.address = it }
        profileUserDto.RH?.let { profile.RH = it }
        profileUserDto.email?.let { profile.email = it }

        return profile
    }

    override fun listProfile(): List<ProfileUser> {
        return profileUserRepository.findAll();
    }

    override fun findByEmail(email: String): ProfileUser? {
       val profile = profileUserRepository.findByEmail(email);
       return if(profile.isPresent) {
           profile.get()
       }else{
           null
       }
    }

}