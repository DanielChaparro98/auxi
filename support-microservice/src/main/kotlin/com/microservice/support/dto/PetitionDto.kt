package com.microservice.support.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
data class PetitionDto (
    val title: String,
    val description: String,
    val status:String
)
