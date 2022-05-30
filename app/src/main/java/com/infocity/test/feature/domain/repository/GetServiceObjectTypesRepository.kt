package com.infocity.test.feature.domain.repository

import com.infocity.test.feature.data.server.response.LoadTotalCountResponse
import com.infocity.test.feature.data.server.response.ServiceObjectTypeResponse

interface GetServiceObjectTypesRepository {

    suspend fun loadTotalCount(accessToken: String): LoadTotalCountResponse


    suspend fun getServiceObject(
        skip: Int,
        take: Int
    ): List<ServiceObjectTypeResponse>
}