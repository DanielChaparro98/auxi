package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import lombok.Builder
import java.util.Date

@Entity
@Table(name = "experiences")
data class Experience(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long = 0,
        val name:String,
        val date: Date,
        val type: String,
        val email: String,
        @Lob
        val data:ByteArray
)
