package com.workspace.playground.core.domain.user.model

import com.workspace.playground.core.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Embedded
    val email: Email,
    @Column(name = "nickname")
    val nickname: String,
) : BaseEntity()
