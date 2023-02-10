package com.example.dictionaryjpapp.feature_dictionary.data.remote.dto

import com.example.dictionaryjpapp.feature_dictionary.data.local.entity.WordInfoEntity
import com.example.dictionaryjpapp.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meaning = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word
        )
    }

    fun toWordInfoEntity() :WordInfoEntity{
        return WordInfoEntity(
            meaning = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word
        )
    }
}