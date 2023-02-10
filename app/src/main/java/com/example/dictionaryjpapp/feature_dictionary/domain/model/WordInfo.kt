package com.example.dictionaryjpapp.feature_dictionary.domain.model

import com.example.dictionaryjpapp.feature_dictionary.data.remote.dto.LicenseDto

data class WordInfo(
    val meaning: List<Meaning>,
    val phonetic: String,
    val word: String
)