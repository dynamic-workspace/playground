package com.workspace.playground.application.bithumb

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.CountDownLatch
import org.slf4j.LoggerFactory
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

private val logger = LoggerFactory.getLogger(BithumbSocketHandler::class.java)

class BithumbSocketHandler: TextWebSocketHandler() {
    private val objectMapper = ObjectMapper()
    lateinit var session: WebSocketSession

    private val closeLatch = CountDownLatch(1)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        this.session = session
        logger.info("[BITHUMB] Got Connect : ${session.id}")
        super.afterConnectionEstablished(session)
    }


    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info("[BITHUMB] Connection closed: ${status.code} - ${status.reason}")
        session.close()
        closeLatch.countDown()
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info("[KIS] Got Message : ${message.payload}")

        val readTree = objectMapper.readTree(message.payload)
        logger.info("[KIS] Got Message : $readTree")


        if (!readTree.has("status")) {
        }
    }
}