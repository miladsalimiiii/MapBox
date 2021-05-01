package com.example.mapboxexample.data.model.image

import androidx.room.TypeConverter
import com.example.mapboxexample.data.model.image.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ImageConvertor {
    @TypeConverter
    fun fromCountryLangList(image: List<Image?>?): String? {
        if (image == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Image?>?>() {}.type
        return gson.toJson(image, type)
    }

    @TypeConverter
    fun toCountryLangList(image: String?): List<Image>? {
        if (image == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Image?>?>() {}.type
        return gson.fromJson<List<Image>>(image, type)
    }
}