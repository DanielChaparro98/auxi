package com.gateway.microservice.entity

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@NoArgsConstructor
@Data
@Builder
data class Token(
    val token:String
)
