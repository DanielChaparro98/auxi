package com.microservice.support.service.impl

import com.microservice.support.entity.Petition
import com.microservice.support.repository.PetitionRepository
import com.microservice.support.service.PetitionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PetitionServiceImpl(@Autowired private val petitionRepository: PetitionRepository): PetitionService {

    override fun savePetition(petition: Petition): Petition {
        if(petitionRepository.findById(petition.id).isPresent){
            throw Exception("La petición ya existe")
        }
        return petitionRepository.save(petition)
    }

    override fun updatePetition(petition: Petition): Petition {
        if(petitionRepository.findById(petition.id).isPresent){
            val optionalPetition = petitionRepository.findById(petition.id)
            optionalPetition.get().title = petition.title
            optionalPetition.get().description = petition.description
            optionalPetition.get().status = petition.status
            return optionalPetition.get()
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