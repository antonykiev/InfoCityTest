package com.infocity.test.feature.domain.usecase

import com.infocity.test.feature.data.store.ServiceObjectType
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetObjectTypes(
    private val repo: GetServiceObjectTypesRepository
) {

    suspend operator fun invoke(id: String? = null): Flow<List<ServiceObjectType>> {
        return repo.getLocalServiceObject()
            .map {
                it.filter { it.parentId == id }
            }
    }
}