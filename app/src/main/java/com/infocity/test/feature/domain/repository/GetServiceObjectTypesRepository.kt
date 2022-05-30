package com.infocity.test.feature.domain.repository

import com.infocity.test.feature.data.server.response.LoadTotalCountResponse

interface GetServiceObjectTypesRepository {

    suspend fun loadTotalCount(): LoadTotalCountResponse


    suspend fun getServiceObject(
        skip: Int,
        take: Int
    ): LoadTotalCountResponse
}