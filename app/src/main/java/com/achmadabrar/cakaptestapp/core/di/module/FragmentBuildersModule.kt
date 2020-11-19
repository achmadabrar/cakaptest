package com.achmadabrar.cakaptestapp.core.di.module

import com.achmadabrar.cakaptestapp.presentation.fragment.AddContactFragment
import com.achmadabrar.cakaptestapp.presentation.fragment.ContactFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeContactFragment(): ContactFragment

    @ContributesAndroidInjector
    abstract fun contributeAddContactFragment(): AddContactFragment
}