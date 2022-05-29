package com.infocity.test.feature.data.repository

import com.infocity.test.feature.data.source.UserSource
import com.infocity.test.feature.data.store.User
import com.infocity.test.feature.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userSource: UserSource
): UserRepository {

    override fun getUser(): Flow<User> = userSource.getUser()

    override suspend fun saveUser(user: User) = userSource.saveUser(user)

}