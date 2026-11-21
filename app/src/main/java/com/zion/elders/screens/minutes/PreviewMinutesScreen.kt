package com.zion.elders.screens.minutes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.backend.Lib
import com.zion.elders.components.SystemForm
import com.zion.elders.components.cardColor
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.previewBoxColor

@Composable
fun PreviewMinutesScreen(
    text: String
){
    val dateToday = Lib.getCurrentDate()
    val totalWordCount = "x"

    SystemForm(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FullPreviewLabel()
            Text(text = "Read-Only Mode", color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = "Total words: ",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = totalWordCount,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "Date: $dateToday",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }

    CustomTextFieldForm {
        TextField(
            value = text.ifBlank { "No minutes logged yet." },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().height(500.dp),
            enabled = false,
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = cardColor,
            ),
            textStyle = TextStyle(
                color = Color.DarkGray,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )
        )
    }
}

@Composable
fun FullPreviewLabel(){
    Box(
        modifier = Modifier.width(95.dp).height(35.dp).clip(RoundedCornerShape(13.dp))
            .background(previewBoxColor),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.fillMaxWidth().padding(3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Circle
            Box(modifier = Modifier.size(15.dp).clip(RoundedCornerShape(80.dp)).background(
                experimentalBackground
            ))
            Text(text = "Preview", fontSize = 13.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}