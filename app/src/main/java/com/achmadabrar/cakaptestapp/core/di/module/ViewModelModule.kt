package com.achmadabrar.cakaptestapp.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.achmadabrar.cakaptestapp.core.di.ViewModelFactory
import com.achmadabrar.cakaptestapp.core.di.ViewModelKey
import com.achmadabrar.cakaptestapp.presentation.viewmodel.ContactViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ContactViewModel::class)
    internal abstract fun mainViewModel(viewModel: ContactViewModel): ViewModel

}