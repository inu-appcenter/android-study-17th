package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val desserts = Datasource.dessertList

    private val _uiState = MutableStateFlow(
        DessertUiState(
            currentDessertImageId = desserts[0].imageId,
            currentDessertPrice = desserts[0].price
        )
    )
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        val newDessertsSold = _uiState.value.dessertsSold + 1
        val newRevenue = _uiState.value.revenue + _uiState.value.currentDessertPrice
        val newDessert = determineDessertToShow(desserts, newDessertsSold)

        _uiState.update { currentState ->
            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold,
                currentDessertImageId = newDessert.imageId,
                currentDessertPrice = newDessert.price
            )
        }
    }

    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}