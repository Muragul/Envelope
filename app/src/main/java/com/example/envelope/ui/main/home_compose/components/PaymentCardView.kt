package com.example.envelope.ui.main.home_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.envelope.ui.theme.purpleGradientDark
import com.example.envelope.ui.theme.purpleGradientLight

@Composable
fun PaymentCardView(
    title: String,
    amount: String,
    image: String,
    onClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        purpleGradientLight,
                        purpleGradientDark
                    )
                )
            )
            .clickable { onClicked.invoke() }
    ) {
        Column(
            modifier = Modifier
                .width(130.dp)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = ColorPainter(Color.Red),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextViewComponent(size = 14, content = title)
            }
            TextViewComponent(size = 14, content = amount)
            Spacer(modifier = Modifier.height(8.dp))
            ButtonComponent("Оплатить")
        }
    }

}