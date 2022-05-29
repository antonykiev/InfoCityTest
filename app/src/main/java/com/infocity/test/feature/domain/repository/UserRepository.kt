package com.infocity.test.feature.domain.repository

import com.infocity.test.feature.data.store.User
import com.infocity.test.feature.domain.data.UserDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<User>

    suspend fun saveUser(user: User)

}