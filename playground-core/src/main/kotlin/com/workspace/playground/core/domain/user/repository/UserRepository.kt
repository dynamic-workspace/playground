package com.workspace.playground.core.domain.user.repository

import com.workspace.playground.core.domain.user.model.User
import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, Long> {
    fun findById(id: Long): User?
    fun save(user: User): User
}