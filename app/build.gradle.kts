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
    implementation (Dependencies.Ktx.core)
    implementation (Dependencies.Androidx.appCompat)
    implementation (Dependencies.Androidx.material)
    implementation (Dependencies.Androidx.constraintLayout)

    implementation (Dependencies.Retrofit.retrofit)
    implementation (Dependencies.Retrofit.okHttp)
    implementation (Dependencies.Retrofit.gsonConverter)

    implementation(Dependencies.Room.room)
    implementation(Dependencies.Room.compiler)
    implementation(Dependencies.Room.ktx)
}