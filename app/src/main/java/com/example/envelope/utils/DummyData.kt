package com.example.envelope.utils

import com.example.envelope.data.*

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
    Service(1, "Onay", ""),
    Service(2, "Beeline", ""),
    Service(3, "Kaspi", ""),
)

val cardsList = listOf(
    Card(
        name = "NAME 1",
        type = "VISA",
        number = "**** **** **** 4444",
        expireDate = "04/23",
        cvv = "654",
        imageUrl = ""
    ),
    Card(
        name = "NAME 2",
        type = "MasterCard",
        number = "**** **** **** 3232",
        expireDate = "12/12",
        cvv = "321",
        imageUrl = ""
    )
)

val defaultUser = User(
    name = "Дидар",
    surname = "Мусаханов",
    gender = "Мужчина",
    age = 27
)


val depositList = listOf(
    Deposit(
        imageUrl = "",
        title = "Сберегательный “Хоум”",
        description = "13,4% годовых"
    ),
    Deposit(
        imageUrl = "",
        title = "Депозит Forte",
        description = "Эффективная ставка 8,7%"
    ),
    Deposit(
        imageUrl = "",
        title = "Депозит Halyk Bank",
        description = "Эффективная ставка до 13%"
    ),
    Deposit(
        imageUrl = "",
        title = "Kaspi Депозит",
        description = "Эффективная ставка 9%"
    )
)