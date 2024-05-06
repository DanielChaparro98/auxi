package com.offermicroservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalTime
import java.util.*

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
data class OfferDto(
    val name:String,
    val nameProfessional: String,
    val description:String,
    var state:String,
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    val date: Date,
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @JsonProperty("start_time")
    val startDate: LocalTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @JsonProperty("final_time")
    val finalDate: LocalTime,
    val email: String,
    val emailUser: String,
    val price: String,
)
