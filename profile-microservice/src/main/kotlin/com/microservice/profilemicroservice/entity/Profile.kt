package com.microservice.profilemicroservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
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
        @OneToMany
        val experiences: List<Experience>,
        @OneToMany
        val studies: List<Studies>
)
