package com.example.envelope.utils

import com.example.envelope.data.Expense
import com.example.envelope.data.OnBoarding
import com.example.envelope.data.Services

val onBoardingList = listOf(
    OnBoarding(
        id = 1,
        imageUrl = "",
        text = "Hac ipsum posuere nulla nunc mattis id purus non egestas. "
    ),
    OnBoarding(
        id = 2,
        imageUrl = "",
        text = "Hac ipsum posuere nulla nunc mattis id purus non egestas. "
    ),
    OnBoarding(
        id = 3,
        imageUrl = "",
        text = "Hac ipsum posuere nulla nunc mattis id purus non egestas. "
    )
)

val expensesList = listOf(
    Expense(1, "Onay", "", 3000),
    Expense(2, "Beeline", "", 2500),
    Expense(3, "Kaspi", "", 9500),
)

val servicesList = listOf(
    Services(1, "Onay", ""),
    Services(2, "Beeline", ""),
    Services(3, "Kaspi", ""),
)