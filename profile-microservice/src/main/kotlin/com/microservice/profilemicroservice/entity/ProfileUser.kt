package com.microservice.profilemicroservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "profiles_user")
data class ProfileUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var RH: String? = null,
    @Lob
    var medicalHistory:  ByteArray? = null,
    var email: String? = null,
)
