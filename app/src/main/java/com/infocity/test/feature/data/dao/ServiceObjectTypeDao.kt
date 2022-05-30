package com.infocity.test.feature.data.dao

import androidx.room.*
import com.infocity.test.feature.data.store.ServiceObjectType
import com.infocity.test.feature.data.store.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceObjectTypeDao {

    @Query("SELECT * FROM serviceobjecttype")
    fun getAll(): Flow<List<ServiceObjectType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(list: List<ServiceObjectType>)

}