package com.infocity.test.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.infocity.test.App
import com.infocity.test.feature.data.InfoCityDataBase
import com.infocity.test.feature.data.dao.UserDao
import com.infocity.test.feature.data.repository.AuthRepositoryImpl
import com.infocity.test.feature.data.repository.GetServiceObjectTypesRepositoryImpl
import com.infocity.test.feature.data.repository.UserRepositoryImpl
import com.infocity.test.feature.data.server.RetrofitImpl
import com.infocity.test.feature.data.server.api.Api
import com.infocity.test.feature.data.source.AuthTokenSource
import com.infocity.test.feature.data.source.GetServiceObjectTypesSource
import com.infocity.test.feature.data.source.UserSource
import com.infocity.test.feature.domain.repository.AuthRepository
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import com.infocity.test.feature.domain.repository.UserRepository
import com.infocity.test.feature.domain.usecase.AuthUser
import com.infocity.test.feature.domain.usecase.GetObjectTypesQuantity
import com.infocity.test.feature.presentation.auth.ViewModelAuth
import com.infocity.test.feature.presentation.service_object_type.ServiceObjectTypeViewModel
import dagger.*
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    ViewModelModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
    DataSourceModule::class,
    RemoteModule::class,
    RoomModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent

    }
}

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelAuth::class)
    abstract fun provideViewModelAuth(viewModel: ViewModelAuth): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ServiceObjectTypeViewModel::class)
    abstract fun provideServiceObjectTypeViewModel(viewModel: ServiceObjectTypeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}

@Module
internal class UseCaseModule {

    @Singleton
    @Provides
    fun provideAuthUser(
        userRepository: UserRepository,
        authRepository: AuthRepository
    ): AuthUser =
        AuthUser(userRepository, authRepository)

    @Singleton
    @Provides
    fun provideGetObjectTypesQuantity(
        remoteRepo: GetServiceObjectTypesRepository,
    ): GetObjectTypesQuantity =
        GetObjectTypesQuantity(remoteRepo)

}

@Module
internal class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(dataSource: AuthTokenSource): AuthRepository =
        AuthRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun provideGetServiceObjectTypesRepository(dataSource: GetServiceObjectTypesSource): GetServiceObjectTypesRepository =
        GetServiceObjectTypesRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun provideUserRepositoryImpl(dataSource: UserSource): UserRepository =
        UserRepositoryImpl(dataSource)
}

@Module
internal class DataSourceModule {

    @Singleton
    @Provides
    fun provideAuthTokenSource(api: Api): AuthTokenSource =
        AuthTokenSource(api)

    @Singleton
    @Provides
    fun provideGetServiceObjectTypesSource(api: Api): GetServiceObjectTypesSource =
        GetServiceObjectTypesSource(api)

    @Singleton
    @Provides
    fun provideUserSource(dao: UserDao): UserSource =
        UserSource(dao)

}

@Module
internal class RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Api =
        RetrofitImpl()

}

@Module
internal class RoomModule {

    private var infoCityDataBase: InfoCityDataBase? = null

    @Singleton
    @Provides
    fun providesRoomDatabase(application: App?): InfoCityDataBase {
        application ?: throw NullPointerException()

        infoCityDataBase =
            Room.databaseBuilder(
                application,
                InfoCityDataBase::class.java,
                InfoCityDataBase.DB_NAME
            ).build()

        return infoCityDataBase!!
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: InfoCityDataBase): UserDao {
        return demoDatabase.userDao
    }

}

@Singleton
class AppViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}