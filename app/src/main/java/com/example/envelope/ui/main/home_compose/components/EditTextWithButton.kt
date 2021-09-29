package com.example.envelope.ui.main.home_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envelope.R
import com.example.envelope.ui.theme.backgroundDark
import com.example.envelope.ui.theme.hintColor
import com.example.envelope.ui.theme.lightBlue

@Composable
fun EditTextWithButton() {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(width = 1.dp, color = lightBlue)
            .fillMaxWidth()
            .height(46.dp)
            .background(backgroundDark)
            .padding(8.dp)
    ) {

        val style = TextStyle(
            color = hintColor,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_regular_400))
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(end = 16.dp)
                    .fillMaxWidth(0.77f),
                onValueChange = {},
                value = "Введите сумму для изъятия",
                textStyle = style,
            )

            ButtonComponent(text = "Изъять")

        }

    }
}