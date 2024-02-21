package com.offermicroservice.entity

import jakarta.persistence.*
import java.util.Date
import java.util.TimeZone

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
    var timeBegin: TimeZone,
    var timeFinal: TimeZone
)