package com.offermicroservice.repository

import com.offermicroservice.entity.Offer
import org.springframework.data.jpa.repository.JpaRepository

interface OfferRepository:JpaRepository<Offer, Long> {
    fun findByState(state: String): List<Offer>;
}