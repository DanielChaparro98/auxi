package com.offermicroservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.*

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
data class OfferDto(
    val name:String,
    val description:String,
    val state:String,
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("start_date")
    val startDate: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("final_date")
    val finalDate: LocalDateTime,
    val email: String
)
