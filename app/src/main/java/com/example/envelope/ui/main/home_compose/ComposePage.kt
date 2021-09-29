package com.example.envelope.ui.main.home_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.envelope.R
import com.example.envelope.ui.main.home_compose.components.EditTextWithButton
import com.example.envelope.ui.main.home_compose.components.MainPageEnvelopeView
import com.example.envelope.ui.main.home_compose.components.PaymentCardView
import com.example.envelope.ui.main.home_compose.components.TextViewComponent
import com.example.envelope.ui.theme.backgroundDark
import com.example.envelope.ui.theme.lightBlue
import com.example.envelope.ui.theme.purpleGradientLight
import com.example.envelope.ui.theme.textDark
import com.example.envelope.utils.budgetList
import com.example.envelope.utils.expensesList

@ExperimentalFoundationApi
@Composable
fun ComposePage(
    onServiceClicked: () -> Unit,
    onPaymentClicked: () -> Unit,
    onDepositClicked: () -> Unit,
    onBudgetClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(backgroundDark)
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 16.dp,
                    bottom = 4.dp,
                    top = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextViewComponent(size = 26, content = "Загружено")
            TextViewComponent(size = 14, content = "29 июля - 29 августа")
        }
        TextViewComponent(
            size = 18,
            content = "151 200 000 ₸",
            textColor = purpleGradientLight
        )

        SetConstraintLayout(onBudgetClicked)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 4.dp,
                    top = 24.dp,
                    end = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextViewComponent(size = 18, content = "Обязательные расходы")
            TextViewComponent(
                size = 14,
                modifier = Modifier
                    .clickable {
                        onServiceClicked.invoke()
                    },
                content = "Все платежи",
                textColor = lightBlue
            )
        }
        TextViewComponent(
            size = 16,
            content = "96 500 ₸",
            textColor = purpleGradientLight
        )

        LazyRow(modifier = Modifier.padding(top = 8.dp)) {
            items(items = expensesList, itemContent = { item ->
                PaymentCardView(
                    title = item.title.toString(),
                    amount = item.totalSum.toString(),
                    image = item.imageUrl.toString(),
                    onClicked = onPaymentClicked
                )
                Spacer(modifier = Modifier.width(8.dp))
            })
        }

        SetExpensesLayout("Unexpected expenses")

        EditTextWithButton()

        SetExpensesLayout("Savings")

        Box(
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(lightBlue)
                .padding(horizontal = 14.dp, vertical = 11.dp)
        ) {
            TextViewComponent(
                size = 14,
                content = "Open deposit",
                textColor = textDark,
                alignment = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDepositClicked.invoke()
                    }
            )
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun SetConstraintLayout(onBudgetClicked: () -> Unit) {
    val constraints = ConstraintSet {
        val envelopeTitle = createRefFor("envelopeTitle")
        val budgetHint = createRefFor("budgetHint")
        val envelopeAmount = createRefFor("envelopeAmount")
        val rvEnvelopes = createRefFor("rvEnvelopes")

        constrain(envelopeTitle) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(budgetHint) {
            top.linkTo(parent.top)
            start.linkTo(envelopeTitle.end)
            width = Dimension.value(24.dp)
            height = Dimension.value(24.dp)
        }

        constrain(envelopeAmount) {
            top.linkTo(envelopeTitle.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(rvEnvelopes) {
            start.linkTo(parent.start)
            top.linkTo(envelopeAmount.bottom)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, end = 8.dp)
    ) {
        TextViewComponent(
            size = 18, content = "Budget for month",
            modifier = Modifier.layoutId("envelopeTitle")
        )
        Image(
            painter = painterResource(R.drawable.ic_question),
            contentDescription = "image",
            modifier = Modifier
                .padding(start = 8.dp)
                .layoutId("budgetHint")
        )
        TextViewComponent(
            size = 16, content = "65 400 ₸",
            textColor = purpleGradientLight,
            modifier = Modifier
                .layoutId("envelopeAmount")
                .padding(top = 4.dp)
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .layoutId("rvEnvelopes")
                .padding(top = 8.dp)
        ) {
            itemsIndexed(budgetList) { index, item ->
                MainPageEnvelopeView(
                    isCurrent = index == 0,
                    week = item.weekNumber.toString() + " week",
                    dates = item.weekRange,
                    amount = item.weekBudget,
                    padding = PaddingValues(end = 8.dp, bottom = 8.dp),
                    onClicked = onBudgetClicked
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun SetExpensesLayout(title: String) {
    val constraints = ConstraintSet {
        val expensesTitle = createRefFor("expensesTitle")
        val expensesHint = createRefFor("expensesHint")
        val expensesAmount = createRefFor("expensesAmount")

        constrain(expensesTitle) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(expensesHint) {
            top.linkTo(parent.top)
            start.linkTo(expensesTitle.end)
            width = Dimension.value(24.dp)
            height = Dimension.value(24.dp)
        }

        constrain(expensesAmount) {
            top.linkTo(expensesTitle.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        TextViewComponent(
            size = 18, content = title,
            modifier = Modifier.layoutId("expensesTitle")
        )
        Image(
            painter = painterResource(R.drawable.ic_question),
            contentDescription = "image",
            modifier = Modifier
                .padding(start = 8.dp)
                .layoutId("expensesHint")
        )
        TextViewComponent(
            size = 16, content = "20 000 ₸",
            textColor = purpleGradientLight,
            modifier = Modifier
                .layoutId("expensesAmount")
                .padding(top = 4.dp)
        )
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    ComposePage({}, {}, {}, {})
}