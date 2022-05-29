package com.infocity.test.feature.data.server.api

import com.infocity.test.feature.data.server.HttpRoutes
import com.infocity.test.feature.data.server.request.UserAuthDto
import com.infocity.test.feature.data.server.response.UserAuthResponse
import retrofit2.http.GET

interface ApiAuthToken {

    @GET(HttpRoutes.AUTH_TOKEN)
    suspend fun authToken(userAuthDto: UserAuthDto): UserAuthResponse

}