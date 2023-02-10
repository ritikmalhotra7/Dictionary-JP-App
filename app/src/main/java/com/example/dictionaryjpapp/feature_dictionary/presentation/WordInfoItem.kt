package com.example.dictionaryjpapp.feature_dictionary.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryjpapp.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo:WordInfo,
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = wordInfo.phonetic,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))
        wordInfo.meaning.forEach{ meaning ->
            Text(
                text = meaning.partOfSpeech,
                fontWeight = FontWeight.Bold,
            )
            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index+1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let{
                    Text(text = "Empample: $it")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}