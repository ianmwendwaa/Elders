package com.example.elders.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataInputField(
    dataInputLabel: String,
    value: String,
    onValueChange:(String)-> Unit,
    modifier: Modifier = Modifier,
    acceptNumericalData: Boolean?=null,
    acceptStringData: Boolean?=null
){
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = dataInputLabel, color = textColor, fontSize = 16.sp)
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = dataInputLabel, color = Color.LightGray, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, keyboardType =
                if (acceptNumericalData == true) KeyboardType.Number else KeyboardType.Unspecified),
            keyboardActions = KeyboardActions(onDone = {
                // Login
            }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                disabledContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent, // Hides border on focus
                unfocusedIndicatorColor = Color.Transparent, // Hides border when idle
            ),
            singleLine = true
        )
    }
}