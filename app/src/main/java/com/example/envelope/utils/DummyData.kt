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
    Expense(
        1,
        "Onay",
        "https://is5-ssl.mzstatic.com/image/thumb/Purple124/v4/87/78/1f/87781f59-23e4-a443-6f16-4ecccbc2a029/source/512x512bb.jpg",
        3000
    ),
    Expense(
        2,
        "Beeline",
        "https://i0.wp.com/apptractor.ru/wp-content/uploads/2014/05/beeline.png?fit=300%2C300&ssl=1",
        2500
    ),
    Expense(
        3,
        "Kaspi",
        "https://upload.wikimedia.org/wikipedia/ru/a/aa/Logo_of_Kaspi_bank.png",
        9500
    ),
)

val servicesList = listOf(
    Service(
        1,
        "Onay",
        "https://is5-ssl.mzstatic.com/image/thumb/Purple124/v4/87/78/1f/87781f59-23e4-a443-6f16-4ecccbc2a029/source/512x512bb.jpg"
    ),
    Service(
        2,
        "Beeline",
        "https://i0.wp.com/apptractor.ru/wp-content/uploads/2014/05/beeline.png?fit=300%2C300&ssl=1"
    ),
    Service(3, "Kaspi", "https://upload.wikimedia.org/wikipedia/ru/a/aa/Logo_of_Kaspi_bank.png"),
)

val cardsList = listOf(
    Card(
        name = "NAME 1",
        type = "VISA",
        number = "**** **** **** 4444",
        expireDate = "04/23",
        cvv = "654",
        imageUrl = "https://infocity.az/wp-content/uploads/2020/07/Visa-logo.jpg"
    ),
    Card(
        name = "NAME 2",
        type = "MasterCard",
        number = "**** **** **** 3232",
        expireDate = "12/12",
        cvv = "321",
        imageUrl = "https://adindex.ru/files2/news/2019_01/230401_5.jpg"
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

val budgetList = listOf(
    Budget(weekNumber = 1, weekRange = "19.07 - 26.07", weekSentDate = 0, weekBudget = "23650"),
    Budget(weekNumber = 2, weekRange = "27.07 - 4.08", weekSentDate = 3, weekBudget = "23650"),
    Budget(weekNumber = 3, weekRange = "27.07 - 4.08", weekSentDate = 7, weekBudget = "23650"),
    Budget(weekNumber = 4, weekRange = "27.07 - 4.08", weekSentDate = 15, weekBudget = "23650"),
)


val transactionHistoryList = listOf<TransactionMarker>(
    TransactionHeader("Вчера"),
    Transaction(date = "", title = "Сбережения", type = "", amount = -20000, icon = ""),
    Transaction(
        date = "",
        title = "Onay",
        type = "",
        amount = -2200,
        icon = "https://www.google.com/url?sa=i&url=http%3A%2F%2Fappvisor.ru%2Fapp%2Fios%2Fonay-51716%2F&psig=AOvVaw0O6pzYN8pyvaS-Af3nrrCn&ust=1630662206432000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPCJmJ-A4PICFQAAAAAdAAAAABAD"
    ),
    TransactionHeader("15.08.2021"),
    Transaction(
        date = "",
        title = "Kaspi Рассрочка",
        type = "",
        amount = -18940,
        icon = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fiphone.apkpure.com%2Fkaspikz%2Fkz.kaspi.mobile&psig=AOvVaw0nK_ng0v0RTrW0R4WdYQII&ust=1630662225085000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJCa_6iA4PICFQAAAAAdAAAAABAD"
    ),
    Transaction(date = "", title = "Конверт 1 недели", type = "", amount = 23650, icon = ""),
)