package com.example.envelope.ui.main.home_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.envelope.ui.theme.lightBlue
import com.example.envelope.ui.theme.textDark

@Composable
fun ButtonComponent(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(lightBlue)
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        TextViewComponent(
            size = 14,
            content = text,
            textColor = textDark,
            alignment = TextAlign.Center
        )
    }

}