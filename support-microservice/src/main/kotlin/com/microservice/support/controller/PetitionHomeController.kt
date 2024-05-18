package com.microservice.support.controller;

import com.microservice.support.dto.PetitionDto
import com.microservice.support.entity.Petition
import com.microservice.support.service.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RestController
@RequestMapping("/petition-home")
@RequiredArgsConstructor
class PetitionHomeController(@Autowired private val petitionService:PetitionService) {

    @PostMapping("/contact-home")
    fun savePetition(@RequestBody petitionDto: PetitionDto): ResponseEntity<Petition> {
        val petitionService = petitionService.savePetition(petitionDto)
        return ResponseEntity.ok().body(petitionService)
    }
}
