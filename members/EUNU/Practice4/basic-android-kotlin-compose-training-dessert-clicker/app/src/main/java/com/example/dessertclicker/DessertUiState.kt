package com.example.dessertclicker

import androidx.annotation.DrawableRes

data class DessertUiState(
    val revenue: Int=0, //총 수입
    val dessertsSold: Int=0,
    @DrawableRes val currentDessertImageId: Int=0,
    val currentDessertPrice: Int =0 //현재 디저트 가격
)