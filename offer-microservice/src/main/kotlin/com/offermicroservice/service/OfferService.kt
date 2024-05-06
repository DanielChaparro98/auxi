package com.offermicroservice.service

import com.offermicroservice.dto.OfferDto
import com.offermicroservice.entity.Offer
import java.util.*
import javax.swing.text.html.Option

interface OfferService {

    fun saveOffer(offerDto: OfferDto):Offer
    fun listOffer(): List<Offer>
    fun deleteOffer(id: Long):String
    fun findById(id:Long): Optional<Offer>
    fun findByState(state:String):List<Offer>
    fun oneOffer(id:Long):Offer
    fun updateOffer(offer: Offer):Offer
    fun findByEmail(email:String):List<Offer>
    fun selectOffer(idOffer: Long, emailUser: String): Offer
    fun findByEmailAndState(email: String, state: String):List<Offer>
    fun findByEmailUser(emailUser:String): List<Offer>;
    fun cancelOffer(idOffer: Long, emailUser: String): Offer;
    fun successOffer(idOffer: Long, emailUser: String): Offer;
    fun validateDateOffer(id:Long): Boolean
}