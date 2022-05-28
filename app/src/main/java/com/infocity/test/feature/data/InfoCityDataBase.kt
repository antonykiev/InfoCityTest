package com.infocity.test.feature.data

import androidx.room.Database
import com.infocity.test.feature.data.dao.UserDao
import com.infocity.test.feature.data.store.User

@Database(
    entities = [
        User::class,
    ],
    version = 1
)
abstract class InfoCityDataBase {

    abstract val noteDao: UserDao

    companion object {
        const val DB_NAME = "info_city_app"
    }
}