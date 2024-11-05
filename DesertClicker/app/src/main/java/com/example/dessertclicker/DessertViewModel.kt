package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DessertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    init {

    }

    /**
     * Determine which dessert to show.
     *
     * 返回要显示的dessert索引
     */
    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for ((index, dessert) in dessertList.withIndex()) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. 
                // Break when we find the first dessert that requires more sales.
                break
            }
        }
        return dessertIndex
    }

    fun onDessertClicked() {

        // Update the revenue
        _uiState.update { state ->
            val desertsSold = state.dessertsSold.inc()
            val nextDessertIndex = determineDessertIndex(desertsSold)

            state.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = state.revenue.plus(state.currentDessertPrice),
                dessertsSold = desertsSold,
                currentDessertPrice = dessertList[nextDessertIndex].price,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
            )
        }

    }

}