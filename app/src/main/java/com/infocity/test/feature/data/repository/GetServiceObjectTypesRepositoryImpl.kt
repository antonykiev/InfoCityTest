package com.infocity.test.feature.data.repository

import com.infocity.test.feature.data.server.response.LoadTotalCountResponse
import com.infocity.test.feature.data.source.AuthTokenSource
import com.infocity.test.feature.data.source.GetServiceObjectTypesSource
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository

class GetServiceObjectTypesRepositoryImpl(
    private val source: GetServiceObjectTypesSource
): GetServiceObjectTypesRepository {


    override suspend fun loadTotalCount(): LoadTotalCountResponse {
        return source.loadTotalCount()
    }

    override suspend fun getServiceObject(skip: Int, take: Int): LoadTotalCountResponse {
        return source.getServiceObject(skip, take)
    }
}