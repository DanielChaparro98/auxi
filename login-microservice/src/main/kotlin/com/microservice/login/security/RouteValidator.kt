package com.microservice.login.security

import com.microservice.login.dto.RequestDto
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@AllArgsConstructor
@ConfigurationProperties(prefix = "professional-paths")
class RouteValidator {
    var paths: List<RequestDto> = emptyList()
        get() {
            return field
        }
        set(value) {
            field = value
        }
    fun isProfessionalPath(dto: RequestDto):Boolean{
        return paths.any { p ->
            p.uri.toRegex().matches(dto.uri) && p.method == dto.method
        }
    }
}