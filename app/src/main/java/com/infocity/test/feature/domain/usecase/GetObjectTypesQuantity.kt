package com.infocity.test.feature.domain.usecase

import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetObjectTypesQuantity(
    private val remoteRepo: GetServiceObjectTypesRepository
) {

    suspend operator fun invoke(): Flow<Int> {
        val  remoteResult = remoteRepo.loadTotalCount().totalCount

        return flow {
            emit(remoteResult)
        }
    }
}