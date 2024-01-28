package com.workspace.playground.application.api.controller

import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SystemController {
    @GetMapping("/health")
    fun health(response: HttpServletResponse) {
        response.status = HttpStatus.OK.value()
    }
}