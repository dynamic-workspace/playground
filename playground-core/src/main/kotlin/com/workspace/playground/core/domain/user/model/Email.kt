package com.workspace.playground.core.domain.user.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Email(
    @Column(name = "email")
    private val value: String,
) {
    companion object {
        fun of(email: String): Email {
            return Email(email)
        }
    }
}
