package com.workspace.playground.application.bithumb

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.util.concurrent.CountDownLatch
import org.slf4j.LoggerFactory
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

private val logger = LoggerFactory.getLogger(BithumbSocketHandler::class.java)

class BithumbSocketHandler: TextWebSocketHandler() {
    private val objectMapper = ObjectMapper().registerKotlinModule()
    lateinit var session: WebSocketSession

    private val closeLatch = CountDownLatch(1)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        this.session = session
        logger.info("[BITHUMB] Got Connect : ${session.id}")
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(
            BithumbSocket(
                type = "ticker",
                symbols = listOf("BTC_KRW"),
                tickTypes = listOf("30M")
            )
        )))
    }


    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info("[BITHUMB] Connection closed: ${status.code} - ${status.reason}")
        session.close()
        closeLatch.countDown()
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val readTree = objectMapper.readTree(message.payload)
        logger.info("[BITHUMB] Got Message : $readTree")

        if (!readTree.has("status")) {
            val response =
                objectMapper.readValue(message.payload, BithumbTicker::class.java)
            logger.info("[BITHUMB] Got Message : $response")
        }
    }


    data class BithumbSocket(
        val type: String,
        val symbols: List<String>,
        val tickTypes: List<String>,
    )
}
