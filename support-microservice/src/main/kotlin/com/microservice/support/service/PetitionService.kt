package com.microservice.support.service

import com.microservice.support.dto.PetitionDto
import com.microservice.support.entity.Petition
import java.util.Optional

interface PetitionService {

    fun savePetition(petitionDto: PetitionDto):Petition
    fun updatePetition(petition: Petition):Petition
    fun deletePetition(id: Long): String
    fun findPetitionById(id:Long):Optional<Petition>
    fun listPetition():List<Petition>
}