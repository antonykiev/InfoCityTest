package com.infocity.test.feature.data.server.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ServiceObjectTypeResponse(
    val id: String,
//    val children: String?,
    val companies: String?,
    val createdAt: String,
    val createdBy: String,
    val deletedAt: String?,
    val deletedBy: String?,
    val geomType: String,
    val hasChildren: Boolean,
    val isActive: Boolean,
    val layerName: String,
    val layerSystemName: String,
    val members: String?,
    val name: String,
    val number: Int,
//    val parent: String?,
    val parentId: String,
    val serviceAttributes: String?,
    val serviceObjects: String?,
    val updatedAt: String,
    val updatedBy: String,
    val workTypes: String?
)