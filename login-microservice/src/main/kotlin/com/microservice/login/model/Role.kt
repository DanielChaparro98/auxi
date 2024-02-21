package com.microservice.login.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data

@Entity
@Data
@Table(name = "roles")
data class Role(
    @Id
    @Column(name = "id_role")
    val id:Long,
    val name:String
)
