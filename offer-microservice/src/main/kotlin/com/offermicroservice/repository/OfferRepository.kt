package com.offermicroservice.repository

import com.offermicroservice.entity.Offer
import org.springframework.data.jpa.repository.JpaRepository

interface OfferRepository:JpaRepository<Offer, Long> {
    fun findByState(state: String): List<Offer>;
    fun findByEmail(email:String): List<Offer>;
    fun findByEmailUser(emailUser:String):List<Offer>
    fun findByEmailAndState(email: String, state: String): List<Offer>
}