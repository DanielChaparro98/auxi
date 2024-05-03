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
    var name:String ? = null,
    var description:String ? = null,
    var state: String ? = null,
    var date: Date ? = null,
    var startTime: LocalTime ? = null,
    var finalDate: LocalTime ? = null,
    var email: String ? = null,
    var emailUser: String ? = null,
    var price: String ? = null
)