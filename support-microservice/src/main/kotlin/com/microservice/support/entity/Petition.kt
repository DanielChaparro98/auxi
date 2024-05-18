package com.microservice.support.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name="petitions")
data class Petition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    var title:String,
    var date: Date,
    var description:String,
    var status:String,
    var email: String
)
