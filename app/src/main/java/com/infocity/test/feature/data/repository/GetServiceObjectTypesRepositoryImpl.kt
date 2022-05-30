package com.infocity.test.feature.data.repository

import com.infocity.test.feature.data.server.api.TokenHeaderProvider
import com.infocity.test.feature.data.server.response.LoadTotalCountResponse
import com.infocity.test.feature.data.server.response.ServiceObjectTypeResponse
import com.infocity.test.feature.data.source.AuthTokenSource
import com.infocity.test.feature.data.source.GetServiceObjectTypesSource
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository

class GetServiceObjectTypesRepositoryImpl(
    private val source: GetServiceObjectTypesSource
): GetServiceObjectTypesRepository {


    override suspend fun loadTotalCount(accessToken: String): LoadTotalCountResponse {
        val validHeader = TokenHeaderProvider.provideHeader(accessToken)
        return source.loadTotalCount(validHeader)
    }

    override suspend fun getServiceObject(skip: Int, take: Int): List<ServiceObjectTypeResponse> {
        return source.getServiceObject(skip, take)
    }
}