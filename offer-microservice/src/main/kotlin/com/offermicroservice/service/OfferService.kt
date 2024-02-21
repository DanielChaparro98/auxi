package com.offermicroservice.service

import com.offermicroservice.dto.OfferDto
import com.offermicroservice.entity.Offer

interface OfferService {

    fun saveOffer(offer: Offer):Offer
    fun listOffer(): List<Offer>
    fun deleteOffer(id: Long):String
    fun findById(id:Long):Boolean
    fun findByState(state:String):List<Offer>
    fun updateOffer(offer: Offer):Offer
}