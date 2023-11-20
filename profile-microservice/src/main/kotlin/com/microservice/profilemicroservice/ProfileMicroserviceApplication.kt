package com.microservice.profilemicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class ProfileMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<ProfileMicroserviceApplication>(*args)
}
