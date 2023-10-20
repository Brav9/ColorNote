package com.khalbro.colornote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.usecase.FillDatabaseWithDefaultValuesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val fillDatabaseWithDefaultValuesUseCase: FillDatabaseWithDefaultValuesUseCase
) : ViewModel() {

    fun fillDatabaseWithDefaultValues() = viewModelScope.launch(Dispatchers.IO) {
        fillDatabaseWithDefaultValuesUseCase.invoke()
    }
}