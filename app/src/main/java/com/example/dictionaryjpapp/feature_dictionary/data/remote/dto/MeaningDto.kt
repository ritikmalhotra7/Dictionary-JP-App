package com.example.dictionaryjpapp.feature_dictionary.data.remote.dto

import com.example.dictionaryjpapp.feature_dictionary.domain.model.Meaning
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map{it.toDefinition()},
            partOfSpeech = partOfSpeech,
            antonyms = antonyms,
            synonyms = synonyms
        )
    }
}