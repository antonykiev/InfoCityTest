package com.infocity.test.di

import com.infocity.test.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        FragmentBuilderModule::class,
    ])
    abstract fun mainActivity(): MainActivity
}