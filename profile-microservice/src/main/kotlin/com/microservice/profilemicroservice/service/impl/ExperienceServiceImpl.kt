package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.repository.ExperienceRepository
import com.microservice.profilemicroservice.service.ExperienceService
import com.microservice.profilemicroservice.util.File
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ExperienceServiceImpl(@Autowired private val experienceRepository: ExperienceRepository, @Autowired private val uploapFile: File):ExperienceService {

    @Transactional
    override fun save(experienceDto: ExperienceDto, file: MultipartFile): Experience {
        var experienceOptional =  experienceRepository.findByName(experienceDto.name)
        val data = uploapFile.store(file)
        if(experienceOptional.isPresent){
            throw Exception("Experiencia ya existe")
        }
        var experience = Experience(
            name = experienceDto.name.lowercase(),
            date = experienceDto.date,
            type = experienceDto.type,
            data = data,
            email = experienceDto.email,
        )
        return experienceRepository.save(experience)
    }

    @Transactional
    override fun update(id: Long, experienceUpdate: ExperienceDto, file: MultipartFile): Experience {
        val experience =
            experienceRepository.findById(id).orElseThrow { EntityNotFoundException("Experiencia no encontrada: $id") }
        val data: ByteArray?;
        if (!file.isEmpty) {
            data = uploapFile.store(file);
            experience.data = data
        }
        experienceUpdate.name?.let {
            if (it.isNotEmpty()) {
                experience.name = it
            }
        }
        experienceUpdate.type?.let {
            if (it.isNotEmpty()) {
                experience.type = it
            }
        }
        experienceUpdate.email?.let {
            if (it.isNotEmpty()) {
                experience.email = it
            }
        }

        return experienceRepository.save(experience)
    }

    override fun findExperience(id: Long): Experience {
        val optional: Optional<Experience> =  experienceRepository.findById(id);
        if(optional.isPresent){
            return optional.get()
        }
        throw Exception("No se encontro experiencia")
    }

    override fun list(): List<Experience> {
        return experienceRepository.findAll()
    }

    @Transactional
    override fun listByEmail(email:String): List<Long> {
        val experienceList = experienceRepository.findByEmail(email)
        val experienceListFilter = mutableListOf<Long>()
        if(experienceList.isNotEmpty()) {
            for (index in experienceList) {
                experienceListFilter.add(index.id)
                return experienceListFilter;
            }
        }
        throw Exception("No existen experiencias")

    }

    @Transactional
    override fun listByEmailObject(email: String): Experience {
        val experienceEmail =  experienceRepository.findByEmail(email)
        if(experienceEmail.isNotEmpty()){
            for(index in experienceEmail){
                return index
            }
        }
        throw Exception("No existen experiencias asociadas")
    }
}