package com.example.dictionaryjpapp.feature_dictionary.data.remote

import com.example.dictionaryjpapp.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getMeaning(
        @Path("word") word: String
    ): Response<List<WordInfoDto>>

    companion object{
        const val BASE_URL = "https://api.dictionaryapi.dev"
    }
}