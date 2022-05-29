package com.infocity.test.feature.data.source

import com.infocity.test.feature.data.dao.UserDao
import com.infocity.test.feature.data.store.User
import kotlinx.coroutines.flow.Flow

class UserSource(private val userDao: UserDao) {

    fun getUser(): Flow<User> = userDao.getUser()

    suspend fun saveUser(user: User) = userDao.updateUser(user)

}