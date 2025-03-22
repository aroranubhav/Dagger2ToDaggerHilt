package com.almax.dagger2todaggerhilt.presentation.gif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almax.dagger2todaggerhilt.data.model.GifData
import com.almax.dagger2todaggerhilt.data.repository.TrendingGifRepository
import com.almax.dagger2todaggerhilt.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val repository: TrendingGifRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<GifData>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<GifData>>>
        get() = _uiState

    init {
        getGifs()
    }

    private fun getGifs() {
        viewModelScope.launch {
            repository.getTrendingGifs()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}