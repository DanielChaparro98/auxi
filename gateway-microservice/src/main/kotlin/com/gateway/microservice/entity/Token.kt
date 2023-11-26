package com.gateway.microservice.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
data class Token @JsonCreator constructor(
    @JsonProperty("token")val token:String
)
