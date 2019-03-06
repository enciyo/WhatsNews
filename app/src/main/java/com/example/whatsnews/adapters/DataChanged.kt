package com.example.whatsnews.adapters

import dagger.Provides
import javax.inject.Singleton

interface DataChanged{
    fun onSucces() : Boolean
}