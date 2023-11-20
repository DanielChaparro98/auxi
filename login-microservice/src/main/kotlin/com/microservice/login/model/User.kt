package com.microservice.login.model

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    val id:Long =0,
    var email:String,
    var password:String,
    @ManyToOne
    var role: Role
)
