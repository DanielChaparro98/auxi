package com.microservice.login.model

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@Builder
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    val id:Long,
    var email:String,
    var password:String,
    @ManyToOne
    var role: Role
)
