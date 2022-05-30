package com.infocity.test.feature.data.server.api

import com.infocity.test.feature.data.server.HttpRoutes
import com.infocity.test.feature.data.server.request.UserAuthDto
import com.infocity.test.feature.data.server.response.UserAuthResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiAuthToken {

    @Headers(value = [
        HttpRoutes.HEADERS_JSON,
        HttpRoutes.HEADERS_JSON_PATCH,
    ])
    @POST(HttpRoutes.AUTH_TOKEN)
    suspend fun authToken(@Body userAuthDto: UserAuthDto): UserAuthResponse

}