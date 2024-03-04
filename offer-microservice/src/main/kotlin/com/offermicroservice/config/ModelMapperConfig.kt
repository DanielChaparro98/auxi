package com.offermicroservice.config

import org.modelmapper.ModelMapper
import org.springframework.boot.Banner.Mode
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {
    @Bean
    fun modelMapper():ModelMapper{
        return ModelMapper()
    }
}