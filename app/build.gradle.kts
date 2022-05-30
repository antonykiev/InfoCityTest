import utils.*

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    compileSdk = Base.currentSDK

    defaultConfig {
        minSdk = Base.minSDK
        targetSdk = Base.currentSDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation  (Dependencies.Ktx.core)
    implementation  (Dependencies.Ktx.activity)
    implementation  (Dependencies.Ktx.fragment)
    implementation  (Dependencies.Androidx.appCompat)
    implementation  (Dependencies.Androidx.material)
    implementation  (Dependencies.Androidx.constraintLayout)

    implementation  (Dependencies.DI.Dagger.dagger)
    implementation  (Dependencies.DI.Dagger.android)
    implementation  (Dependencies.DI.Dagger.support)
    kapt            (Dependencies.DI.Dagger.compiler)
    kapt            (Dependencies.DI.Dagger.processor)

    implementation  (Dependencies.LifeCycle.runtime)
    implementation  (Dependencies.LifeCycle.livedata)
    implementation  (Dependencies.LifeCycle.viewModel)

    implementation  (Dependencies.WorkManager.workRuntimeKtx)

    implementation  (Dependencies.Retrofit.retrofit)
    implementation  (Dependencies.Retrofit.okHttp)
    implementation  (Dependencies.Retrofit.interceptor)
    implementation  (Dependencies.Retrofit.gsonConverter)

    implementation  (Dependencies.Room.room)
    implementation  (Dependencies.Room.ktx)
    kapt            (Dependencies.Room.compiler)

    implementation  (Dependencies.Navigation.navigationRuntimeKtx)
    implementation  (Dependencies.Navigation.navigationFragmentKtx)
    implementation  (Dependencies.Navigation.navigationUiKtx)

    implementation  (Dependencies.Binding.delegate)

    implementation  (Dependencies.Mapper.mapStruct)
    kapt            (Dependencies.Mapper.mapStructKapt)

}