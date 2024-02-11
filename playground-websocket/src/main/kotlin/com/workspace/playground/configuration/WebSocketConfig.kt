package com.workspace.playground.configuration

import com.workspace.playground.application.bithumb.BithumbSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.client.WebSocketConnectionManager
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        val client = StandardWebSocketClient()
        try {
            client.taskExecutor!!.execute {
                val manager = WebSocketConnectionManager(
                    client,
                    BithumbSocketHandler(),
                    "wss://pubwss.bithumb.com/pub/ws"
                )
                manager.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
