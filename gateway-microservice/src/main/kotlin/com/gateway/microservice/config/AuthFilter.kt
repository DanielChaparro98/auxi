package com.gateway.microservice.config

import com.gateway.microservice.entity.Token
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
class AuthFilter(private val webClient: WebClient.Builder):AbstractGatewayFilterFactory<AuthFilter.config>() {

    val webClientDos: WebClient.Builder = webClient

    override fun apply(config: config?): GatewayFilter {
        return GatewayFilter{exchange,chain->
            if(!exchange.request.headers.containsKey(HttpHeaders.AUTHORIZATION)){
                return@GatewayFilter onError(exchange,HttpStatus.BAD_REQUEST)
            }
            val tokenHeader = exchange.request.headers[HttpHeaders.AUTHORIZATION]!![0]
            val chunks = tokenHeader.split(" ")
            if(chunks.size != 2 || chunks[0] != "Bearer"){
                return@GatewayFilter onError(exchange,HttpStatus.BAD_REQUEST)
            }
            return@GatewayFilter webClient.build()
                .post()
                .uri("http://auth-service/auth/validate?token="+chunks[1])
                .retrieve().bodyToMono(Token::class.java)
                .map { t ->
                    t.token
                    exchange
                }
                .flatMap(chain::filter)
        }

    }

    fun onError(exchange: ServerWebExchange, status: HttpStatus): Mono<Void> {
        val response: ServerHttpResponse = exchange.response
        response.setStatusCode(status)
        return response.setComplete()

    }

    class config{}
}