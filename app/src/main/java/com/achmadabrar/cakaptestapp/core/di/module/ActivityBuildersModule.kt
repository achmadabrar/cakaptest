package com.achmadabrar.cakaptestapp.core.di.module

import com.achmadabrar.cakaptestapp.presentation.activity.ContactActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesContactActivity(): ContactActivity
}