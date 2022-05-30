package com.infocity.test.feature.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.infocity.test.feature.data.dao.ServiceObjectTypeDao
import com.infocity.test.feature.data.dao.UserDao
import com.infocity.test.feature.data.store.ServiceObjectType
import com.infocity.test.feature.data.store.User

@Database(
    entities = [
        User::class,
        ServiceObjectType::class,
    ],
    version = 1
)
abstract class InfoCityDataBase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val serviceObjectTypeDao: ServiceObjectTypeDao

    companion object {
        const val DB_NAME = "info_city_app"
    }
}