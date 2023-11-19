package com.microservice.login.model

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
data class Token(
    val token: String
)
