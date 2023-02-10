package com.example.dictionaryjpapp.core.repositories

import com.example.dictionaryjpapp.core.utils.ResponseState
import com.example.dictionaryjpapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word:String): Flow<ResponseState<List<WordInfo>>>
}