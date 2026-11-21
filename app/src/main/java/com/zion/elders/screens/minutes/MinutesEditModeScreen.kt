package com.zion.elders.screens.minutes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.R
import com.zion.elders.backend.Lib
import com.zion.elders.components.SystemForm
import com.zion.elders.ui.theme.disabledButtonColor
import com.zion.elders.ui.theme.experimentalBackground

@Composable
fun MinutesEditModeScreen(
    text: String,
    onMinutesUpdated: (String) -> Unit
){
    var wordCount by remember { mutableIntStateOf(0) }
    val dateToday = Lib.getCurrentDate()
    SystemForm(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {

        // Text formatting features row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            TextFormattingFeature(formattingIcon = R.drawable.format_bold) { }
            TextFormattingFeature(formattingIcon = R.drawable.format_italic) { }
            TextFormattingFeature(formattingIcon = R.drawable.format_underlined_squiggle) { }

            Spacer(modifier = Modifier
                .height(25.dp)
                .width(1.dp)
                .background(Color.LightGray))
            TextFormattingFeature(formattingIcon = R.drawable.format_list_bullets) { }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.LightGray))

        // Word counter and date display
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = "Words: ",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = wordCount.toString(),
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
    // This was originally a SystemForm, but I didn't like its padding in this particular section
    CustomTextFieldForm {
        TextField(
            value = text,
            onValueChange = onMinutesUpdated,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Transparent,
                cursorColor = experimentalBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                lineHeight = 24.sp
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            )
        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            enabled = text.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = experimentalBackground,
                disabledContainerColor = disabledButtonColor,
                disabledContentColor = Color.LightGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Save minutes", fontSize = 15.sp, color = Color.White)
        }
    }
}