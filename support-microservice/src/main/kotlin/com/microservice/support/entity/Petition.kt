package com.microservice.support.entity

import jakarta.persistence.*

@Entity
@Table(name="petitions")
data class Petition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    var title:String,
    var description:String,
    var status:String
)
