package com.zion.elders.screens.minutes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.components.common.SystemForm
import com.zion.elders.components.cardColor
import com.zion.elders.components.minutes.CustomTextFieldForm
import com.zion.elders.components.minutes.FullPreviewLabel
import com.zion.elders.viewmodels.minuteseditor.EditorData

@Composable
fun PreviewMinutesScreen(
    minutes: String,
    managerViewModelState: EditorData
){
    val dateToday = managerViewModelState.dateOfLogging
    val totalWordCount = managerViewModelState.numberOfWords

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
                    text = totalWordCount.toString(),
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
            value = minutes.ifBlank { "No minutes logged yet." },
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