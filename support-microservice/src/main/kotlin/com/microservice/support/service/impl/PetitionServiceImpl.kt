package com.microservice.support.service.impl

import com.microservice.support.dto.PetitionDto
import com.microservice.support.entity.Petition
import com.microservice.support.repository.PetitionRepository
import com.microservice.support.service.PetitionService
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
        if(petitionRepository.findById(petition.id).isPresent){
            val optionalPetition = petitionRepository.findById(petition.id)
            optionalPetition.get().title = petition.title
            optionalPetition.get().date = petition.date
            optionalPetition.get().description = petition.description
            optionalPetition.get().status = petition.status
            return petitionRepository.save(optionalPetition.get())
        }
        throw Exception("Error al actualizar petición")
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