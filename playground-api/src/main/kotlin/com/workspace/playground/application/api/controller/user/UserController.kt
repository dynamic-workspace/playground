package com.workspace.playground.application.api.controller.user

import com.workspace.playground.core.domain.user.service.UserService
import org.springframework.web.bind.annotation.RestController

@RestController("/user")
class UserController(
    private val userService: UserService,
)