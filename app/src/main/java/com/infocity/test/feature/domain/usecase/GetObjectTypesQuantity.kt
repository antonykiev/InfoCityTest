package com.infocity.test.feature.domain.usecase

import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import com.infocity.test.feature.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetObjectTypesQuantity(
    private val userRepository: UserRepository,
    private val remoteRepo: GetServiceObjectTypesRepository
) {

    suspend operator fun invoke(): Flow<Int> {
        val accessToken = userRepository.getUser().first().accessToken!!

        val  remoteResult = remoteRepo.loadTotalCount(accessToken).totalCount

        return flow {
            emit(remoteResult)
        }
    }
}