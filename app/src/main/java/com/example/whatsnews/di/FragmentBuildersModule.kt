package com.example.whatsnews.di

import com.example.whatsnews.ui.TopHeadline
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeTopFragment(): TopHeadline


}