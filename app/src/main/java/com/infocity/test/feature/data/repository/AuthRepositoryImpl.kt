package com.infocity.test.feature.data.repository

import com.infocity.test.feature.data.server.request.UserAuthDto
import com.infocity.test.feature.data.server.response.UserAuthResponse
import com.infocity.test.feature.data.source.AuthTokenSource
import com.infocity.test.feature.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authTokenSource: AuthTokenSource
): AuthRepository {

    override suspend fun authToken(userAuthDto: UserAuthDto): UserAuthResponse {
        return authTokenSource.authToken(userAuthDto)
    }

}