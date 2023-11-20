package com.microservice.login.service.impl

import com.microservice.login.dto.UserDto
import com.microservice.login.model.Role
import com.microservice.login.model.Token
import com.microservice.login.model.User
import com.microservice.login.repository.RoleRepository
import com.microservice.login.repository.UserRepository
import com.microservice.login.security.JwtProvider
import com.microservice.login.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.swing.text.html.Option

@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository
    ,@Autowired private val passwordEncoder: PasswordEncoder
    ,@Autowired private val jwtProvider: JwtProvider
    ,@Autowired private val roleRepository: RoleRepository): UserService {

    override fun saveUser(user: UserDto): User {
        val userOptional: Optional<User> = userRepository.findByEmail(user.email)
        if (userOptional.isPresent){
            throw Exception("Usuario ya existente")
        }
        val password:String = passwordEncoder.encode(user.password)
        val roleOptional:Optional<Role> = roleRepository.findByName(user.role)
        val userDto = User( email = user.email, password = password, role = roleOptional.get());
        return userRepository.save(userDto);
    }

    override fun login(user: UserDto): Token {
            val userOptional: Optional<User> = userRepository.findByEmail(user.email)
        if (!userOptional.isPresent){
            throw Exception("Usuario no encontrado")
        }
        if(passwordEncoder.matches(user.password,userOptional.get().password)){
            return Token(jwtProvider.createToken(userOptional.get()))
        }
        throw Exception("Error en el Login")
    }

    override fun validate(token: String): Token {
        if(!jwtProvider.validate(token)){
            throw Exception("Error con el token")
        }
        val email:String = jwtProvider.getEmailFromToken(token)
        if (!userRepository.findByEmail(email).isPresent){
            throw Exception("Usuario no encontrado")
        }
        return Token(token)
    }



}