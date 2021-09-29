package com.example.envelope.ui.main.home_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.envelope.ui.theme.lightBlue
import com.example.envelope.ui.theme.purpleGradientDark
import com.example.envelope.ui.theme.purpleGradientLight

@Composable
fun MainPageEnvelopeView(
    isCurrent: Boolean,
    week: String,
    dates: String,
    amount: String,
    padding: PaddingValues? = null,
    onClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding ?: PaddingValues(0.dp))
            .clip(RoundedCornerShape(5.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        purpleGradientLight,
                        purpleGradientDark
                    )
                )
            )
            .clickable {
                onClicked.invoke()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .background(if (isCurrent) lightBlue else Color.Transparent)
                    .width(4.dp)
                    .fillMaxHeight()
            )
            Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextViewComponent(size = 14, content = week)
                    TextViewComponent(size = 14, content = dates)
                }
                Spacer(modifier = Modifier.height(4.dp))
                TextViewComponent(size = 18, content = amount)
            }
        }
    }
}