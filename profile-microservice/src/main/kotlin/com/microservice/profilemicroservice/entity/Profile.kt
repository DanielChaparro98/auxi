package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "profiles")
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String,
        val phone: String,
        val studyType: String,
        val beginSchedule: String,
        val endSchedule:String,
        val zone: String,
        val email: String,
        @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @JoinColumn(name = "id_profile", referencedColumnName = "id")
        val experiences: List<Experience>,
        @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @JoinColumn(name = "id_profile", referencedColumnName = "id")
        val studies: List<Studies>
)
