package com.example.dictionaryjpapp.feature_dictionary.data.remote.dto.repository

import android.util.Log
import com.example.dictionaryjpapp.core.repositories.WordInfoRepository
import com.example.dictionaryjpapp.core.utils.ResponseState
import com.example.dictionaryjpapp.feature_dictionary.data.local.WordInfoDao
import com.example.dictionaryjpapp.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionaryjpapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordInfoRepositoryImpl(private val api: DictionaryApi, private val dao: WordInfoDao) :
    WordInfoRepository {
    override fun getWordInfo(word: String): Flow<ResponseState<List<WordInfo>>> = flow {
        emit(ResponseState.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        //caching
        emit(ResponseState.Loading(data = wordInfos))
        try {
            val remoteWordInfos = api.getMeaning(word).body()
            dao.deleteWordInfo(remoteWordInfos!!.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: Exception) {
            Log.d("taget",e.message.toString())
            emit(ResponseState.Error(message = e.message?:"", data = wordInfos))
        }
        val newWordInfos = dao.getWordInfos(word).map{ it.toWordInfo() }
        emit(ResponseState.Success(newWordInfos))
    }
}