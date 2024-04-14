package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import lombok.Builder
import java.util.Date

@Entity
@Table(name = "studies")
data class Studies(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long = 0,
        val name:String,
        val date: Date,
        val type: String,
        val email: String,
        @Lob
        val diploma: ByteArray,
        @Lob
        val rethus: ByteArray,
        @Lob
        val resolution: ByteArray
)
