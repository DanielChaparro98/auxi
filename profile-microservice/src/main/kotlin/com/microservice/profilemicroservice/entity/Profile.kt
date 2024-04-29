package com.microservice.profilemicroservice.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "profiles")
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var name: String? = null,
        var phone: String? = null,
        var studyType: String? = null,
        var beginSchedule: String? = null,
        var endSchedule:String? = null,
        var zone: String? = null,
        var email: String? = null,
        @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @JoinColumn(name = "id_profile", referencedColumnName = "id")
        val experiences: List<Experience>,
        @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        @JoinColumn(name = "id_profile", referencedColumnName = "id")
        val studies: List<Studies>
)
