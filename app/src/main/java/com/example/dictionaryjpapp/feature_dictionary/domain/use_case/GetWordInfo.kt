package com.example.dictionaryjpapp.feature_dictionary.domain.use_case

import com.example.dictionaryjpapp.core.repositories.WordInfoRepository
import com.example.dictionaryjpapp.core.utils.ResponseState
import com.example.dictionaryjpapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository:WordInfoRepository
) {
    operator fun invoke(word:String): Flow<ResponseState<List<WordInfo>>> {
        if(word.isBlank()){
            return flow{}
        }
        return repository.getWordInfo(word)
    }
}