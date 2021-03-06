package com.infocity.test.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.infocity.test.App
import com.infocity.test.feature.data.InfoCityDataBase
import com.infocity.test.feature.data.dao.ServiceObjectTypeDao
import com.infocity.test.feature.data.dao.UserDao
import com.infocity.test.feature.data.repository.AuthRepositoryImpl
import com.infocity.test.feature.data.repository.GetServiceObjectTypesRepositoryImpl
import com.infocity.test.feature.data.repository.UserRepositoryImpl
import com.infocity.test.feature.data.server.RetrofitImpl
import com.infocity.test.feature.data.server.api.Api
import com.infocity.test.feature.data.source.AuthTokenSource
import com.infocity.test.feature.data.source.GetServiceObjectTypesLocalSource
import com.infocity.test.feature.data.source.GetServiceObjectTypesRemoteSource
import com.infocity.test.feature.data.source.UserSource
import com.infocity.test.feature.domain.repository.AuthRepository
import com.infocity.test.feature.domain.repository.GetServiceObjectTypesRepository
import com.infocity.test.feature.domain.repository.UserRepository
import com.infocity.test.feature.domain.usecase.AuthUser
import com.infocity.test.feature.domain.usecase.GetObjectTypes
import com.infocity.test.feature.domain.usecase.GetObjectTypesQuantityLoader
import com.infocity.test.feature.presentation.auth.ViewModelAuth
import com.infocity.test.feature.presentation.service_object_type.ServiceObjectTypeFragment
import com.infocity.test.feature.presentation.service_object_type.ServiceObjectTypeViewModel
import dagger.*
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    FragmentBuilderModule::class,
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
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeServiceObjectTypeFragment(): ServiceObjectTypeFragment

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
    ): GetObjectTypesQuantityLoader =
        GetObjectTypesQuantityLoader(remoteRepo)

    @Singleton
    @Provides
    fun provideGetObjectTypes(
        remoteRepo: GetServiceObjectTypesRepository,
    ): GetObjectTypes =
        GetObjectTypes(remoteRepo)

}

@Module
internal class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(dataSource: AuthTokenSource): AuthRepository =
        AuthRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun provideGetServiceObjectTypesRepository(
        dataSourceLocal: GetServiceObjectTypesLocalSource,
        dataSourceRemote: GetServiceObjectTypesRemoteSource,
        userSource: UserSource
    ): GetServiceObjectTypesRepository =
        GetServiceObjectTypesRepositoryImpl(dataSourceLocal, dataSourceRemote, userSource)

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
    fun provideGetServiceObjectTypesSource(api: Api): GetServiceObjectTypesRemoteSource =
        GetServiceObjectTypesRemoteSource(api)

    @Singleton
    @Provides
    fun provideUserSource(dao: UserDao): UserSource =
        UserSource(dao)

    @Singleton
    @Provides
    fun provideGetServiceObjectTypesLocalSource(dao: ServiceObjectTypeDao): GetServiceObjectTypesLocalSource =
        GetServiceObjectTypesLocalSource(dao)

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
    fun providesDao(demoDatabase: InfoCityDataBase): UserDao {
        return demoDatabase.userDao
    }

    @Singleton
    @Provides
    fun providesServiceObjectTypeDao(demoDatabase: InfoCityDataBase): ServiceObjectTypeDao {
        return demoDatabase.serviceObjectTypeDao
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