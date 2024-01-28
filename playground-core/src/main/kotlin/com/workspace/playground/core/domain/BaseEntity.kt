package com.workspace.playground.core.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@MappedSuperclass
abstract class BaseEntity(
    @Column(name = "created_at", insertable = false, updatable = false)
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", insertable = false, updatable = false)
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)