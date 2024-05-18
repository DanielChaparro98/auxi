package com.offermicroservice.controller

import com.offermicroservice.dto.OfferDto
import com.offermicroservice.entity.Offer
import com.offermicroservice.service.OfferService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder

@CrossOrigin("*")
@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
class OfferController(@Autowired private val offerService: OfferService) {

    @PostMapping("/save")
    fun saveOffer(@RequestBody offerDto: OfferDto):ResponseEntity<Offer>{
        val offerSave = offerService.saveOffer(offerDto)
        return ResponseEntity.ok().body(offerSave)
    }

    @PostMapping("/update")
    fun updateOffer(@RequestBody offer: Offer):ResponseEntity<Offer>{
        val offerUpdate = offerService.updateOffer(offer)
        return ResponseEntity.ok().body(offerUpdate)
    }

    @PostMapping("/delete")
    fun deleteOffer(@RequestParam id: Long):ResponseEntity<String>{
        val offerDelete = offerService.deleteOffer(id)
        return ResponseEntity.ok().body(offerDelete)
    }

    @GetMapping("/find_offer")
    fun findByOfferId(@RequestParam id:Long):ResponseEntity<Offer>{
        val offer = offerService.oneOffer(id)
        return ResponseEntity.ok().body(offer)
    }

    @GetMapping("/find_state")
    fun findByState(@RequestParam state: String):ResponseEntity<List<Offer>>{
        val offerState = offerService.findByState(state)
        return ResponseEntity.ok().body(offerState)
    }

    @GetMapping("/list")
    fun listOffer():ResponseEntity<List<Offer>>{
        val offers = offerService.listOffer()
        return ResponseEntity.ok().body(offers)
    }

    @GetMapping("/findByEmail")
    fun findByEmail(@RequestParam email:String):ResponseEntity<List<Offer>>{
        val emailDecode = URLDecoder.decode(email,"UTF-8")
        val offers = offerService.findByEmail(emailDecode)
        return ResponseEntity.ok().body(offers)
    }

    @PostMapping("/selectOffer")
    fun selectOffer(@RequestParam idOffer: Long, @RequestParam emailUser: String): ResponseEntity<Offer>{
        val emailDecode = URLDecoder.decode(emailUser, "UTF-8")
        val findByOffer = offerService.selectOffer(idOffer, emailDecode)
        return ResponseEntity.ok().body(findByOffer)
    }

    @PostMapping("/findByEmailAndOffer")
    fun filterEmailAndOffer(@RequestParam email: String, @RequestParam state: String): ResponseEntity<List<Offer>>{
        val emailDecode =  URLDecoder.decode(email, "UTF-8")
        val filter = offerService.findByEmailAndState(emailDecode,state)
        return ResponseEntity.ok().body(filter)
    }

    @GetMapping("/findByEmailUser")
    fun findEmailUser(@RequestParam emailUser: String): ResponseEntity<List<Offer>>{
        val emailDecode =  URLDecoder.decode(emailUser, "UTF-8")
        val filter =  offerService.findByEmailUser(emailDecode)
        return ResponseEntity.ok().body(filter)
    }

    @PostMapping("/cancelOffer")
    fun cancelOffer(@RequestParam id: Long, @RequestParam email: String): ResponseEntity<Offer>{
        val emailDecode = URLDecoder.decode(email, "UTF-8")
        val filter = offerService.cancelOffer(id,emailDecode)
        return ResponseEntity.ok().body(filter)
    }

    @PostMapping("/successOffer")
    fun successOffer(@RequestParam id: Long, @RequestParam email: String): ResponseEntity<Offer>{
        val emailDecode = URLDecoder.decode(email, "UTF-8")
        val filter = offerService.successOffer(id, emailDecode)
        return ResponseEntity.ok().body(filter)
    }

    @GetMapping("/validateDate")
    fun validateDate(@RequestParam id:Long):ResponseEntity<Boolean>{
        var date =  offerService.validateDateOffer(id)
        return ResponseEntity.ok().body(date)
    }
}