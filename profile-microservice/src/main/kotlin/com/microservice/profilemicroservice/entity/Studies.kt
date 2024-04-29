package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import lombok.Builder
import lombok.ToString
import java.util.Date

@Entity
@Table(name = "studies")
data class Studies(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long = 0,
        var name:String? = null,
        var date: Date? = null,
        var type: String? = null,
        var email: String? = null,
        @Lob
        var diploma: ByteArray? = null,
        @Lob
        var rethus: ByteArray? = null,
        @Lob
        var resolution: ByteArray? = null,
)
