package com.zion.elders.styles

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeadingText(
    text: String,
    textAlignCenter: Boolean ?= false
){
    Text(text = text, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black, textAlign =
    if (textAlignCenter == true) TextAlign.Center else TextAlign.Unspecified)
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun SecondaryHeadingText(
    text: String
){
    Text(text = text, fontSize = 19.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
    Spacer(modifier = Modifier.height(5.dp))
}