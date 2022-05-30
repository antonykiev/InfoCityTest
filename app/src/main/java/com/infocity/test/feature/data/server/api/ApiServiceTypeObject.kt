package com.infocity.test.feature.data.server.api

import com.infocity.test.feature.data.server.HttpRoutes
import com.infocity.test.feature.data.server.response.LoadTotalCountResponse
import retrofit2.http.*

interface ApiServiceTypeObject {

    @Headers(value = [
        HttpRoutes.HEADERS_JSON,
        HttpRoutes.HEADERS_JSON_PATCH,
    ])
    @GET(HttpRoutes.GET_SERVICE_OBJECT_TYPES)
    suspend fun loadTotalCount(
        @Query("RequireTotalCount") isRequired: Boolean = true,
        @Query("Skip") skip: Int = 0,
        @Query("Take") take: Int = 1
    ): LoadTotalCountResponse


    @Headers(value = [
        HttpRoutes.HEADERS_JSON,
        HttpRoutes.HEADERS_JSON_PATCH,
    ])
    @GET(HttpRoutes.GET_SERVICE_OBJECT_TYPES)
    suspend fun getServiceObject(
        @Query("Skip") skip: Int,
        @Query("Take") take: Int
    ): LoadTotalCountResponse

}