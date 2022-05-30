package com.infocity.test.feature.domain.usecase

import com.infocity.test.feature.data.server.api.ApiServiceTypeObject
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetObjectTypesQuantity(
    private val repo: GetServiceObjectTypesRepository
) {

    suspend operator fun invoke(): Flow<Int> {

        val remoteResult = repo.loadTotalCount().totalCount
        val localResultSize = runCatching {
            repo.getLocalServiceObject().first().size
        }.getOrElse { 0 }

        if (localResultSize < remoteResult) {
            loadRemote(remoteResult)
        }

        return flow {
            emit(remoteResult)
        }
    }

    private suspend fun loadRemote(totalCount: Int) {
        buildTakeListRequest(totalCount)
            .map { repo.getRemoteServiceObject(it) }
            .forEach { repo.saveAll(it) }
    }

    private fun buildTakeListRequest(totalCount: Int): List<Int> =
        (0 ..(totalCount / ApiServiceTypeObject.TAKE))
        .map { it * ApiServiceTypeObject.TAKE }
}