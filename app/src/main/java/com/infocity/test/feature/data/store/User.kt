package com.infocity.test.feature.data.store

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val password: String,
    val accessToken: String? = null
)
