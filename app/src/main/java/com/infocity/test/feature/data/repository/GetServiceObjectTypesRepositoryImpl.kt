package com.infocity.test.feature.data.repository

import com.infocity.test.feature.data.mapper.MapperServiceObjectType
import com.infocity.test.feature.data.server.api.TokenHeaderProvider
import com.infocity.test.feature.data.server.response.LoadTotalCountResponse
import com.infocity.test.feature.data.server.response.ServiceObjectTypeResponse
import com.infocity.test.feature.data.source.GetServiceObjectTypesLocalSource
import com.infocity.test.feature.data.source.GetServiceObjectTypesRemoteSource
import com.infocity.test.feature.data.source.UserSource
import com.infocity.test.feature.data.store.ServiceObjectType
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.mapstruct.factory.Mappers

class GetServiceObjectTypesRepositoryImpl(
    private val sourceLocal: GetServiceObjectTypesLocalSource,
    private val sourceRemote: GetServiceObjectTypesRemoteSource,
    private val userSource: UserSource,
): GetServiceObjectTypesRepository {

    private val converter = Mappers.getMapper(MapperServiceObjectType::class.java)



    override suspend fun loadTotalCount(): LoadTotalCountResponse {
        val accessToken = userSource.getUser().first().accessToken!!
        val validHeader = TokenHeaderProvider.provideHeader(accessToken)
        return sourceRemote.loadTotalCount(validHeader)
    }

    override suspend fun getRemoteServiceObject(
        skip: Int,
        take: Int
    ): List<ServiceObjectTypeResponse> {
        val accessToken = userSource.getUser().first().accessToken!!

        val validHeader = TokenHeaderProvider.provideHeader(accessToken)
        return sourceRemote.getServiceObject(validHeader, skip, take).data
    }

    override suspend fun getLocalServiceObject(): Flow<List<ServiceObjectType>> = sourceLocal.getAll()

    override suspend fun saveAll(list: List<ServiceObjectTypeResponse>) {
        val entityList = converter.responseListToEntityList(list)
        sourceLocal.update(entityList)
    }
}