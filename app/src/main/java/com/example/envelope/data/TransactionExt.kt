package com.example.envelope.data

data class TransactionHeader(
    val date: String
) : TransactionMarker

interface TransactionMarker

fun List<Transaction>.toSortedList() {
}