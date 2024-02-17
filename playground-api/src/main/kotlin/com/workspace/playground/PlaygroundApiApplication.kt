package com.workspace.playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaygroundApiApplication

fun main(args: Array<String>) {
    runApplication<PlaygroundApiApplication>(*args)
}

// 사용자가