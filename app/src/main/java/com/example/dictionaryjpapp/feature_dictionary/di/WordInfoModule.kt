package com.example.dictionaryjpapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase.*
import com.example.dictionaryjpapp.core.repositories.WordInfoRepository
import com.example.dictionaryjpapp.feature_dictionary.data.local.Converters
import com.example.dictionaryjpapp.feature_dictionary.data.local.WordInfoDatabase
import com.example.dictionaryjpapp.feature_dictionary.data.local.utils.GsonParser
import com.example.dictionaryjpapp.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionaryjpapp.feature_dictionary.data.remote.DictionaryApi.Companion.BASE_URL
import com.example.dictionaryjpapp.feature_dictionary.data.remote.dto.repository.WordInfoRepositoryImpl
import com.example.dictionaryjpapp.feature_dictionary.domain.use_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideDatabase(ctx: Application): WordInfoDatabase =
        Room.databaseBuilder(ctx, WordInfoDatabase::class.java, "word_db")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo =
        GetWordInfo(repository)

    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, db: WordInfoDatabase): WordInfoRepository =
        WordInfoRepositoryImpl(api, db.dao)

    @Provides
    @Singleton
    fun provideApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}