package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.StudiesDto
import com.microservice.profilemicroservice.entity.Studies
import com.microservice.profilemicroservice.repository.StudiesRepository
import com.microservice.profilemicroservice.service.StudiesService
import com.microservice.profilemicroservice.util.File
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class StudiesServiceImpl(@Autowired private val studiesRepository: StudiesRepository, @Autowired private val uploadFile:File):StudiesService {

    @Transactional
    override fun saveStudy(studiesDto: StudiesDto, diploma: MultipartFile, rethus: MultipartFile, resolution: MultipartFile): Studies {
        val studyOptional =  studiesRepository.findByName(studiesDto.name)
        val diplomaData = uploadFile.store(diploma)
        val rethusData = uploadFile.store(rethus)
        val resolutionData = uploadFile.store(resolution)
        if(!studyOptional.isPresent){
            var studies = Studies(
                name = studiesDto.name.lowercase(),
                date = studiesDto.date,
                type = studiesDto.type,
                email = studiesDto.email,
                diploma = diplomaData,
                rethus = rethusData,
                resolution = resolutionData,
            )
            return studiesRepository.save(studies)

        }
        throw Exception("Estudio ya existe")
    }

    @Transactional
    override fun updateStudy(
        id: Long,
        studiesUpdate: StudiesDto,
        diploma: MultipartFile,
        rethus: MultipartFile,
        resolution: MultipartFile
    ): Studies {
        val studies =
            studiesRepository.findById(id).orElseThrow { EntityNotFoundException("Experiencia no encontrada: $id") }

        val diplomaData: ByteArray?;
        val rethusData: ByteArray?;
        val resolutionData: ByteArray?;

        if (!diploma.isEmpty) {
            diplomaData = uploadFile.store(diploma)
            studies.diploma = diplomaData
        }

        if (!rethus.isEmpty) {
            rethusData = uploadFile.store(rethus)
            studies.rethus = rethusData
        }

        if (!resolution.isEmpty) {
            resolutionData = uploadFile.store(resolution)
            studies.resolution = resolutionData
        }

        studiesUpdate.name?.let { studies.name = it }
        studiesUpdate.date?.let { studies.date = it }
        studiesUpdate.type?.let { studies.type = it }
        studiesUpdate.email?.let { studies.email = it }

        return studies
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

    @Transactional
    override fun findByEmail(email: String): List<Long> {
        val studiesList = studiesRepository.findByEmail(email)
        val studiesFilterList = mutableListOf<Long>()
        for (index in studiesList) {
            studiesFilterList.add(index.id)
        }
        return studiesFilterList
    }

    @Transactional
    override fun listByEmailObject(email: String): Studies {
        val studiesListEmail =  studiesRepository.findByEmail(email)
        for (index in studiesListEmail) {
            return index
        }
        throw Exception("No existen estudios relacionados")
    }
}