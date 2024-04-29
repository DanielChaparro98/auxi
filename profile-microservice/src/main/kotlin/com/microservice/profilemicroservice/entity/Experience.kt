package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import lombok.Builder
import lombok.ToString
import java.util.Date

@Entity
@Table(name = "experiences")
data class Experience(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long = 0,
        var name:String? = null,
        var date: Date? = null,
        var type: String? = null,
        var email: String? = null,
        @Lob
        var data:ByteArray? = null,
)
