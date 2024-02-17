package com.workspace.playground.infrastructure.kafka

import com.workspace.playground.core.domain.transaction.TransactionPrice
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate

interface RealtimeTransactionPriceEventProducer {

    fun send(payload: TransactionPrice)

    class Default(
        private val topic: String,
        private val kafkaTemplate: KafkaTemplate<String?, TransactionPrice>,
    ) : RealtimeTransactionPriceEventProducer {
        override fun send(payload: TransactionPrice) {
            val producerRecord: ProducerRecord<String?, TransactionPrice> =
                ProducerRecord(topic, payload)
            kafkaTemplate.send(producerRecord)
        }
    }
}
