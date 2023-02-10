package com.example.dictionaryjpapp.feature_dictionary.domain.model

import com.example.dictionaryjpapp.feature_dictionary.data.remote.dto.DefinitionDto

data class Meaning(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
