package com.infocity.test.feature.data.store

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int = 1,
    val login: String,
    val password: String,
    val accessToken: String? = null
)
