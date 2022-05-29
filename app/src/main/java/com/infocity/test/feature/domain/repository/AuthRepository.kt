package com.infocity.test.feature.domain.repository

import com.infocity.test.feature.data.server.request.UserAuthDto
import com.infocity.test.feature.data.server.response.UserAuthResponse

interface AuthRepository {

    suspend fun authToken(userAuthDto: UserAuthDto): UserAuthResponse

}