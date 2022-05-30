package com.infocity.test.feature.domain.usecase

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
            repo.getRemoteServiceObject(0, 10)
        }


        return flow {
            emit(remoteResult)
        }
    }
}