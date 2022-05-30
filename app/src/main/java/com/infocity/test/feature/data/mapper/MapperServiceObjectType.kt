package com.infocity.test.feature.data.mapper

import com.infocity.test.feature.data.server.response.ServiceObjectTypeResponse
import com.infocity.test.feature.data.store.ServiceObjectType
import org.mapstruct.Mapper

@Mapper
interface MapperServiceObjectType {

    fun responseToEntity(response: ServiceObjectTypeResponse): ServiceObjectType
    fun responseListToEntityList(response: List<ServiceObjectTypeResponse>): List<ServiceObjectType>

}