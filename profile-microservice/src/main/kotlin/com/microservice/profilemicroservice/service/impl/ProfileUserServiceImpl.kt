package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ProfileUserDto
import com.microservice.profilemicroservice.entity.ProfileUser
import com.microservice.profilemicroservice.repository.ProfileUserRepository
import com.microservice.profilemicroservice.service.ProfileUserService
import com.microservice.profilemicroservice.util.File
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ProfileUserServiceImpl(
    @Autowired private val profileUserRepository: ProfileUserRepository,
    @Autowired private val uploapFile: File
) : ProfileUserService {
    override fun save(profileUserDto: ProfileUserDto, file: MultipartFile): ProfileUser {
        var optionalProfileUser: Optional<ProfileUser> = profileUserRepository.findByEmail(profileUserDto.email)
        if(optionalProfileUser.isPresent){
            throw Exception("Perfil ya existe")
        }
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

        profileUserDto.name?.let {
            if (it.isNotEmpty()) {
                profile.name = it
            }
        }
        profileUserDto.phone?.let {
            if (it.isNotEmpty()) {
                profile.phone = it
            }
        }
        profileUserDto.address?.let {
            if (it.isNotEmpty()) {
                profile.address = it
            }
        }
        profileUserDto.RH?.let {
            if (it.isNotEmpty()) {
                profile.RH = it
            }
        }
        profileUserDto.email?.let {
            if (it.isNotEmpty()) {
                profile.email = it
            }
        }

        return profileUserRepository.save(profile)
    }

    @Transactional
    override fun listProfile(): List<ProfileUser> {
        return profileUserRepository.findAll();
    }

    @Transactional
    override fun findByEmail(email: String): ProfileUser? {
       val profile = profileUserRepository.findByEmail(email);
       return if(profile.isPresent) {
           profile.get()
       }else{
           null
       }
    }

}