package com.microservice.login.security

import com.microservice.login.dto.RequestDto
import com.microservice.login.model.User
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import java.lang.Exception
import java.util.Base64
import java.util.Date
import java.util.Objects

@Component
class JwtProvider(@Autowired private val routeValidator: RouteValidator) {

    @Value("{jwt.secret}}")
    var secret:String = "secret";
    val secretBytes = secret.toByteArray()
    @PostConstruct
    fun init(){
        secret = Base64.getEncoder().encodeToString(secretBytes)
    }

    fun createToken(authUser: User):String{
        var claims:MutableMap<String,Any> = HashMap()
        claims = Jwts.claims().setSubject(authUser.email)
        claims["id"] = authUser.id
        claims["role"] = authUser.role.name
        val now: Date= Date()
        val exp:Date =Date(now.time + 36000000)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun validate(token:String, requestDto: RequestDto):Boolean{
        return try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !(!isProfessional(token)!! && routeValidator.isProfessionalPath(dto = requestDto))
        }catch (e:Exception){
            false;
        }

    }

    fun getEmailFromToken(token:String):String{
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
        }catch (e:Exception){
            return "bad token"
        }
    }

    fun isProfessional(token: String): Boolean? {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.get("role")?.equals("professional")
    }
}