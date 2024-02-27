package com.microservice.support.controller

import com.microservice.support.entity.Petition
import com.microservice.support.service.PetitionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/petition")
class PetitionController(@Autowired private val petitionService: PetitionService) {

    @PostMapping("/save")
    fun savePetition(@RequestBody petition: Petition): ResponseEntity<Petition>{
        val petitionService = petitionService.savePetition(petition)
        return ResponseEntity.ok().body(petitionService)
    }

    @PostMapping("/update")
    fun updatePetition(@RequestBody petition: Petition): ResponseEntity<Petition>{
        val petitionService = petitionService.updatePetition(petition)
        return ResponseEntity.ok().body(petitionService)
    }

    @PostMapping("/delete")
    fun deletePetition(@RequestBody id: Long): ResponseEntity<String>{
        val petitionService = petitionService.deletePetition(id)
        return ResponseEntity.ok().body(petitionService)
    }

    @GetMapping("/list")
    fun listPetition():ResponseEntity<List<Petition>>{
        val petitions = petitionService.listPetition()
        return ResponseEntity.ok().body(petitions)
    }

    @GetMapping("/find_by_id")
    fun findById(@RequestParam id:Long): ResponseEntity<Petition>{
        val optionalPetition = petitionService.findPetitionById(id).get()
        return ResponseEntity.ok().body(optionalPetition)
    }
}