package com.example.whatsnews.db.converters

import androidx.room.TypeConverter
import com.example.whatsnews.model.Article
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*


class ArticleConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Article> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Article>>() {

        }.type

        return gson.fromJson<List<Article>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Article>): String {
        return gson.toJson(someObjects)
    }



}