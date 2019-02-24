package com.example.whatsnews.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whatsnews.ui.TopHeadlineViewModel
import com.example.whatsnews.viewmodel.WhatsNewsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory:WhatsNewsViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlineViewModel::class)
    abstract fun bindUserViewModel(topHeadlineViewModel: TopHeadlineViewModel): ViewModel
}