package com.workspace.playground.configuration

import com.workspace.playground.infrastructure.kis.KisApiClient
import feign.Feign.Builder
import feign.codec.ErrorDecoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignClientBeans {
    @Bean
    fun kisApiClient(
        @Value("\${playground.kis.api-url}") url: String,
        feignBuilder: Builder,
        jacksonEncoder: JacksonEncoder,
        jacksonDecoder: JacksonDecoder,
    ): KisApiClient {
        return feignBuilder
            .encoder(jacksonEncoder)
            .decoder(jacksonDecoder)
            .errorDecoder(ErrorDecoder.Default())
            .target(KisApiClient::class.java, url)
    }
}