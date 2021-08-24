package com.example.envelope.data

data class Transaction(
    val date: String,
    val title: String,
    val type: String,
    val amount: Int,
    val icon: String
) : TransactionMarker
