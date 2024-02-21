package com.offermicroservice.dto

import java.util.*

data class OfferDto(
    val name:String,
    val description:String,
    val state:String,
    val date: Date,
    val timeBegin: TimeZone,
    val timeFinal: TimeZone
)
