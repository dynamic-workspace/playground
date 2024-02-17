package com.workspace.playground.application.stock

import com.fasterxml.jackson.databind.ObjectMapper
import com.workspace.playground.core.domain.transaction.TransactionPrice
import com.workspace.playground.infrastructure.kafka.RealtimeTransactionPriceEventProducer
import org.slf4j.LoggerFactory
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

private val logger = LoggerFactory.getLogger(StockSocketHandler::class.java)

class StockSocketHandler(
    private val objectMapper: ObjectMapper,
    private val realtimeTransactionPriceEventProducer: RealtimeTransactionPriceEventProducer,
    private val approvalKey: String,
) : TextWebSocketHandler() {
    private lateinit var session: WebSocketSession

    override fun afterConnectionEstablished(session: WebSocketSession) {
        this.session = session
        logger.info("[KIS] Got Connect : ${session.id}")
        buildWebSocket()
    }

    private fun buildWebSocket() {
        val header = HashMap<String, String>()
        header["approval_key"] = approvalKey
        header["custtype"] = "P"
        header["tr_type"] = "1"
        header["content_type"] = "utf-8"

        val body = HashMap<String, Map<String, String>>()
        val input = HashMap<String, String>()
        input["tr_id"] = "H0STCNT0"
        input["tr_key"] = "348370" // 엔켐
        body["input"] = input

        val request = HashMap<String, Any>()
        request["header"] = header
        request["body"] = body
        val jsonValue = objectMapper.writeValueAsString(request)
        session.sendMessage(TextMessage(jsonValue))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info("[KIS] Got Message : ${message.payload}")
        val stockInfo = message.payload.split("\\^")

        if (stockInfo.size > 1) {
            logger.info("해당 주식 현재 가격 : ${stockInfo[1]}")
            val payload = TransactionPrice()
            realtimeTransactionPriceEventProducer.send(payload)
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info("[KIS] Connection closed: ${status.code} - ${status.reason}")
        session.close()
    }
}
