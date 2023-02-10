package com.example.dictionaryjpapp.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryjpapp.core.utils.ResponseState
import com.example.dictionaryjpapp.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel@Inject constructor(
    private val getWordInfo: GetWordInfo
): ViewModel() {

    private val _searchedQuery = mutableStateOf<String>("")
    val searchedQuery: State<String> get() = _searchedQuery

    //this is to handle states of result
    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> get() = _state

    //this is to update UI Events
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFLow get() = _eventFlow

    sealed class UIEvent {
        data class ShowSnackBar(val message:String):UIEvent()
    }

    private var searchJob: Job? = null
    fun onSearch(query:String){
        _searchedQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(query).onEach { result ->
                when(result){
                    is ResponseState.Loading ->{
                        _state.value = state.value.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is ResponseState.Error -> {
                        _state.value = state.value.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(result.message?:"Unknown error"))
                    }
                    is ResponseState.Success ->{
                        _state.value = state.value.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}