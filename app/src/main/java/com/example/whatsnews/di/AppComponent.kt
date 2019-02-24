package com.example.whatsnews.di

import android.content.Context
import com.example.whatsnews.App
import com.example.whatsnews.ui.TopHeadline
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent  {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(context: Context) : Builder
        fun build() : AppComponent
    }

    fun inject(app:App)
    fun inject(topHeadline: TopHeadline)
}