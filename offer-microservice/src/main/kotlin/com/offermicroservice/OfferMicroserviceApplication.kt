package com.offermicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class OfferMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<OfferMicroserviceApplication>(*args)
}
