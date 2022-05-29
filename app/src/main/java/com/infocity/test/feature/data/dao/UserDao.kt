package com.infocity.test.feature.data.dao

import androidx.room.*
import com.infocity.test.feature.data.store.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}