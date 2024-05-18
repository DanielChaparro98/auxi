package com.microservice.support.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.Date

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
data class PetitionDto (
    val title: String,
    val date: Date,
    val description: String,
    val status:String,
    val email: String
)
