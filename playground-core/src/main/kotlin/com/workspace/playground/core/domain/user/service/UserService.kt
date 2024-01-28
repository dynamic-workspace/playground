package com.workspace.playground.core.domain.user.service

import com.workspace.playground.core.domain.user.model.User
import com.workspace.playground.core.domain.user.repository.UserRepository
import com.workspace.playground.core.global.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUser(id: Long): User {
        return userRepository.findById(id) ?: throw NotFoundException("id에 해당하는 유저를 찾을 수 없습니다.")
    }
}