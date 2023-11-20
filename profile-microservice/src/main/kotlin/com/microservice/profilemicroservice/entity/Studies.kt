package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import lombok.Builder
import java.util.Date

@Entity
@Table(name = "studies")
data class Studies(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Long,
        val name:String,
        val dateGraduation: Date
)
