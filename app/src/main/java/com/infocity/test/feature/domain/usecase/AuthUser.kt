package com.infocity.test.feature.domain.usecase

import android.util.Log
import com.infocity.test.feature.data.server.request.UserAuthDto
import com.infocity.test.feature.data.server.response.UserAuthResponse
import com.infocity.test.feature.data.store.User
import com.infocity.test.feature.domain.repository.AuthRepository
import com.infocity.test.feature.domain.repository.UserRepository
import kotlinx.coroutines.flow.*

class AuthUser(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Flow<User> {

        val user: User = runCatching {
            userRepository.getUser().firstOrNull()!!
        }.getOrElse {
            val testUser = buildTestUser()
            userRepository.saveUser(testUser)
            return@getOrElse testUser
        }

        val updatedToken = updateToken(user)
        val validUser = user.copy(
            accessToken = updatedToken.accessToken
        )
        userRepository.saveUser(validUser)

        return userRepository.getUser()
            .filterNotNull()
            .distinctUntilChanged()
            .filter { !it.accessToken.isNullOrEmpty() }
    }

    private suspend fun updateToken(user: User): UserAuthResponse {
        return authRepository.authToken(
            UserAuthDto(
                user.login,
                user.password
            )
        )
    }

    private fun buildTestUser(): User = User(
        login = "test",
        password = "QQQqqq@2"
    )
}