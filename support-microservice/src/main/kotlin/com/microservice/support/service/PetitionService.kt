package com.microservice.support.service

import com.microservice.support.entity.Petition
import java.util.Optional

interface PetitionService {

    fun savePetition(petition: Petition):Petition
    fun updatePetition(petition: Petition):Petition
    fun deletePetition(id: Long): String
    fun findPetitionById(id:Long):Optional<Petition>
    fun listPetition():List<Petition>
}