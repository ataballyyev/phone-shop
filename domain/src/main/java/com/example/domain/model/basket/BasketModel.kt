package com.example.domain.model.basket

data class BasketModel(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)