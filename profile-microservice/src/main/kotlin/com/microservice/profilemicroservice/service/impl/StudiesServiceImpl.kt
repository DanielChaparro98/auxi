package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.repository.StudiesRepository
import com.microservice.profilemicroservice.service.StudiesService
import com.microservice.profilemicroservice.util.File
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class StudiesServiceImpl(@Autowired private val studiesRepository: StudiesRepository, @Autowired private val uploadFile:File):StudiesService {

    @Transactional
    override fun saveStudy(studiesDto: StudiesDto, file: MultipartFile): Studies {
        val studyOptional =  studiesRepository.findByName(studiesDto.name)
        val data = uploadFile.store(file)
        if(studyOptional.isPresent){
            throw Exception("Estudio ya existe")
        }
        var studies = Studies(name = studiesDto.name.lowercase() ,date = studiesDto.date ,type = studiesDto.type,data = data, email = studiesDto.email)
        return studiesRepository.save(studies)
    }

    override fun findByStudy(id: Long): Studies {
        val studyOptional: Optional<Studies> = studiesRepository.findById(id);
        if(studyOptional.isPresent){
            return studyOptional.get()
        }
        throw Exception("No existe estudio")
    }

    override fun listStudies(): List<Studies> {
        return studiesRepository.findAll()
    }

    override fun listFilter(email: String): List<Studies> {
        val studies:List<Studies> = studiesRepository.findAll();
        val studiesFiler = mutableListOf<Studies>()
        for(index in studies){
            if(index.email == email){
                studiesFiler.add(index)
            }
        }
        return studiesFiler;
    }
}