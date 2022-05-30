package com.infocity.test.feature.data.server.request

data class ServiceObjectTypes(
    val children: String,
    val companies: String,
    val createdAt: String,
    val createdBy: String,
    val deletedAt: String,
    val deletedBy: String,
    val geomType: String,
    val hasChildren: Boolean,
    val id: String,
    val isActive: Boolean,
    val layerName: String,
    val layerSystemName: String,
    val members: String,
    val name: String,
    val number: Int,
    val parent: String,
    val parentId: String,
    val serviceAttributes: String,
    val serviceObjects: String,
    val updatedAt: String,
    val updatedBy: String,
    val workTypes: String
)