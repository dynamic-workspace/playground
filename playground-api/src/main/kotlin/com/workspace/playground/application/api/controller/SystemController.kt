package com.workspace.playground.application.api.controller

import javax.servlet.http.HttpServletResponse
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SystemController(
    private val env: Environment,
) {
    @GetMapping("/health")
    fun health(response: HttpServletResponse) {
        response.status = HttpStatus.OK.value()
    }

    @GetMapping("/config")
    fun config(): String {
        return "port(server.port)=${env.getProperty("local.server.port")}," +
            " spring.datasource.driver-class-name= ${env.getProperty("spring.datasource.driver-class-name")}"
    }
}