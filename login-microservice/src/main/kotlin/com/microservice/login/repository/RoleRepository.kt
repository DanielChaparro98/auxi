package com.microservice.login.repository

import com.microservice.login.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface RoleRepository: JpaRepository<Role,Long>{
    fun findByName(name:String): Optional<Role>
}