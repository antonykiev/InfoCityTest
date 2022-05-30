package com.infocity.test.feature.domain.repository

import com.infocity.test.feature.data.server.response.LoadTotalCountResponse
import com.infocity.test.feature.data.server.response.ServiceObjectTypeResponse
import com.infocity.test.feature.data.store.ServiceObjectType
import kotlinx.coroutines.flow.Flow

interface GetServiceObjectTypesRepository {

    suspend fun loadTotalCount(): LoadTotalCountResponse


    suspend fun getRemoteServiceObject(
        skip: Int,
        take: Int
    ): List<ServiceObjectTypeResponse>

    suspend fun getLocalServiceObject(): Flow<List<ServiceObjectType>>

    suspend fun saveAll(list: List<ServiceObjectType>)
}