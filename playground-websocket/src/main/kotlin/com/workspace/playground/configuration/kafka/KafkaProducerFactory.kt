package com.workspace.playground.configuration.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import kotlin.reflect.KClass

@Configuration
class KafkaProducerFactory(
    @Value("\${playground.kafka.service.product.bootstrap.servers}")
    private val bootstrapServers: String,
    private val objectMapper: ObjectMapper = jacksonObjectMapper(),
) {
    private fun producerConfigs(bootstrapServers: String): MutableMap<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.BATCH_SIZE_CONFIG] = 16384
        props[ProducerConfig.LINGER_MS_CONFIG] = 0 // 1
        props[ProducerConfig.SEND_BUFFER_CONFIG] = 128 * 1024
        props[ProducerConfig.MAX_REQUEST_SIZE_CONFIG] = 1 * 1024 * 1024
        props[ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG] = 50
        props[ProducerConfig.MAX_BLOCK_MS_CONFIG] = 60 * 1000
        props[ProducerConfig.BUFFER_MEMORY_CONFIG] = 32 * 1024 * 1024
        props[ProducerConfig.COMPRESSION_TYPE_CONFIG] = "none"
        props[ProducerConfig.RETRIES_CONFIG] = 3
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG] = 30 * 1000
        return props
    }

    fun <T : Any> createKafkaTemplate(
        clazz: KClass<T>? = null,
        customConfig: Map<String, Any>? = null,
    ): KafkaTemplate<String?, T> {
        val config = producerConfigs(bootstrapServers)
        customConfig?.entries?.forEach {
            config[it.key] = it.value
        }

        val jsonSerializer = JsonSerializer<T>(objectMapper)
        jsonSerializer.isAddTypeInfo = false

        val factory = DefaultKafkaProducerFactory(
            config,
            StringSerializer(),
            jsonSerializer
        )

        return KafkaTemplate(factory)
    }
}
