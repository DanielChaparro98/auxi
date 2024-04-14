package com.offermicroservice.entity

import jakarta.persistence.*
import java.time.LocalTime
import java.util.Date

@Entity
@Table(name="offers")
data class Offer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    var name:String,
    var description:String,
    var state: String,
    var date: Date,
    var startTime: LocalTime,
    var finalDate: LocalTime,
    var email: String,
    var price: String
)