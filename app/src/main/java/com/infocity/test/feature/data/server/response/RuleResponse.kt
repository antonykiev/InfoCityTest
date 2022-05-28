package com.infocity.test.feature.data.server.response

data class RuleResponse(
    val canCreate: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val canRead: Boolean,
    val isCrudOperationRule: Boolean,
    val memberId: String,
    val numberId: Int,
    val ruleCode: String,
    val ruleId: String
)