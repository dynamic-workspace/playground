package com.workspace.playground.core.global.exception

data class NotFoundException(override val message: String) : RuntimeException(message)
