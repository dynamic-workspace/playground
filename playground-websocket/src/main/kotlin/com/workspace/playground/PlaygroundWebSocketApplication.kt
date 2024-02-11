package com.workspace.playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaygroundWebSocketApplication

fun main(args: Array<String>) {
    runApplication<PlaygroundWebSocketApplication>(*args)
}
