package com.almax.dagger2todaggerhilt.ui.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almax.dagger2todaggerhilt.data.model.CoinResponse
import com.almax.dagger2todaggerhilt.data.repository.CoinRepository
import com.almax.dagger2todaggerhilt.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CoinRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<CoinResponse>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<CoinResponse>>>
        get() = _uiState

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCoins().catch { e ->
                _uiState.value = UiState.Error(e.message.toString())
            }.collect { coins ->
                _uiState.value = UiState.Success(coins)
            }
        }
    }
}