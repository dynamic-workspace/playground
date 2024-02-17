package com.workspace.playground.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.workspace.playground.application.stock.StockSocketHandler
import com.workspace.playground.infrastructure.kafka.RealtimeTransactionPriceEventProducer
import com.workspace.playground.infrastructure.kis.KisApiClient
import com.workspace.playground.infrastructure.kis.request.GetApprovalKeyRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.client.WebSocketConnectionManager
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class KisWebSocketConfiguration(
    private val objectMapper: ObjectMapper = jacksonObjectMapper(),
    private val realtimeTransactionPriceEventProducer: RealtimeTransactionPriceEventProducer,
    private val kisApiClient: KisApiClient,
    @Value("\${playground.kis.api-key}")
    private val appKey: String,
    @Value("\${playground.kis.secret-key}")
    private val secretKey: String,
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val response = kisApiClient.getWebSocketApprovalKey(
            request = GetApprovalKeyRequest(
                appKey = appKey,
                secretKey = secretKey,
            )
        )

        val client = StandardWebSocketClient()
        val manager = WebSocketConnectionManager(
            client,
            StockSocketHandler(
                objectMapper = objectMapper,
                realtimeTransactionPriceEventProducer = realtimeTransactionPriceEventProducer,
                approvalKey = response.approvalKey,
            ),
            "ws://ops.koreainvestment.com:21000",
        )
        manager.isAutoStartup = true
        manager.start()
    }
}
