package com.example.envelope.ui.main.home_compose.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.envelope.R
import com.example.envelope.ui.theme.white

@Composable
fun TextViewComponent(
    size: Int,
    content: String,
    textColor: Color? = white,
    modifier: Modifier? = null,
    alignment: TextAlign? = null
) {
    val fontFamily = FontFamily(
        Font(R.font.nunito_regular_400)
    )
    Text(
        text = content,
        color = textColor ?: white,
        fontSize = size.sp,
        textAlign = alignment ?: TextAlign.Start,
        fontFamily = fontFamily,
        modifier = modifier ?: Modifier
    )

}