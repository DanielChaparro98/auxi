package com.microservice.profilemicroservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.entity.Studies
import jakarta.persistence.OneToMany
import java.util.*

data class ProfileDto(
    val number: String,
    val name: String,
    val phone: String,
    @JsonProperty("study_type")
    val studyType: String,
    val schedule: String,
    val zone: String,
    //@JsonDeserialize(contentAs = Experience::class)
    val experiences: List<Long>,
    //@JsonDeserialize(contentAs = Studies::class)
    val studies: List<Long>
)
