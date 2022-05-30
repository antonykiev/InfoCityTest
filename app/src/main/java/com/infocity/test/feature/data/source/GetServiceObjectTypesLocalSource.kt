package com.infocity.test.feature.data.source

import com.infocity.test.feature.data.dao.ServiceObjectTypeDao
import com.infocity.test.feature.data.store.ServiceObjectType
import kotlinx.coroutines.flow.Flow

class GetServiceObjectTypesLocalSource(private val dao: ServiceObjectTypeDao) {

    fun getAll(): Flow<List<ServiceObjectType>> = dao.getAll()

    suspend fun update(list: List<ServiceObjectType>) = dao.update(list)

}