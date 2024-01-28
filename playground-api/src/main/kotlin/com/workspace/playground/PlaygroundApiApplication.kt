package com.workspace.playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.workspace"])
class PlaygroundApiApplication

fun main(args: Array<String>) {
    runApplication<PlaygroundApiApplication>(*args)
}
