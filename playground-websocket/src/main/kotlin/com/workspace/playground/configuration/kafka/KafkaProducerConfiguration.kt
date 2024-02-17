package com.workspace.playground.configuration.kafka

import com.workspace.playground.core.domain.transaction.TransactionPrice
import com.workspace.playground.infrastructure.kafka.RealtimeTransactionPriceEventProducer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaProducerConfiguration(
    private val kafkaProducerFactory: KafkaProducerFactory,
    @Value("\${playground.kafka.topics.realtime-transaction-price-event}")
    private val realtimeTransactionPriceEvent: String,
) {
    @Bean
    fun realtimeTransactionPriceEventProducer(): RealtimeTransactionPriceEventProducer {
        val kafkaTemplate =
            kafkaProducerFactory.createKafkaTemplate<TransactionPrice>()
        return RealtimeTransactionPriceEventProducer.Default(realtimeTransactionPriceEvent, kafkaTemplate)
    }
}
