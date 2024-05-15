package com.microservice.support.service.impl

import com.microservice.support.dto.PetitionDto
import com.microservice.support.entity.Petition
import com.microservice.support.repository.PetitionRepository
import com.microservice.support.service.PetitionService
import jakarta.persistence.EntityNotFoundException
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PetitionServiceImpl(@Autowired private val petitionRepository: PetitionRepository, @Autowired private val modelMapper: ModelMapper): PetitionService {

    override fun savePetition(petitionDto: PetitionDto): Petition {
        val petition:Petition = modelMapper.map(petitionDto, Petition::class.java)
        return petitionRepository.save(petition)
    }

    override fun updatePetition(petition: Petition): Petition {
        val petitionId = petitionRepository.findById(petition.id)
            .orElseThrow { EntityNotFoundException("Petición no encontrada: $petition.id") }

        petition.title?.let {
            if (it.isNotEmpty()) {
                petitionId.title
            }
        }
        petition.date?.let { petitionId.date = it }
        petition.description?.let {
            if (it.isNotEmpty()) {
                petitionId.description = it
            }
        }
        petition.status?.let {
            if (it.isNotEmpty()) {
                petitionId.status = it
            }
        }
        return petitionRepository.save(petitionId)

    }

    override fun deletePetition(id: Long): String{
        try {
            petitionRepository.deleteById(id)
        }catch (e: Exception){
            throw Exception("Error al eliminar petición")
        }
        return "Petición eliminada"
    }

    override fun findPetitionById(id: Long): Optional<Petition> {
        val petitionOptional = petitionRepository.findById(id)
        return petitionOptional
    }

    override fun listPetition(): List<Petition> {
        return petitionRepository.findAll()
    }
}