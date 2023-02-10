package com.example.dictionaryjpapp.feature_dictionary.presentation

import com.example.dictionaryjpapp.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryjpapp.feature_dictionary.domain.use_case.GetWordInfo

data class WordInfoState(
    val wordInfoItem:List<WordInfo> = emptyList(),
    val isLoading :Boolean = false
)