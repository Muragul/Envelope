package com.example.envelope.data

data class Card(
    val name: String,
    val type: String,
    val number: String,
    val expireDate: String,
    val cvv: String,
    val imageUrl: String
)