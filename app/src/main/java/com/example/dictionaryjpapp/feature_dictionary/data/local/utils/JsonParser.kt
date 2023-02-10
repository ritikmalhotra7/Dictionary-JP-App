package com.example.dictionaryjpapp.feature_dictionary.data.local.utils

import java.lang.reflect.Type

interface JsonParser {
    fun <T> fromJson(json:String, type:Type):T?
    fun <T> toJson(obj:T, type:Type):String?
}