package com.example.elders.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elders.components.DataInputField
import com.example.elders.components.textColor
import com.example.elders.styles.HeadingText
import com.example.elders.styles.SecondaryHeadingText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateMeetingEntryScreen(){
    val dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    var memberName by remember { mutableStateOf("") }
    var membersContribution by remember { mutableStateOf("") }
    var emergingIssues by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(start = 20.dp, top = 35.dp, end = 10.dp)
    ) {
        HeadingText(text = "Meeting entry for $dateToday")

        SecondaryHeadingText(text = "Contributions:")
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            DataInputField("Member name",
                value = memberName,
                onValueChange = {
                    memberName = it
                },
                modifier = Modifier.width(180.dp)
            )

            Spacer(modifier = Modifier.width(3.dp))
            DataInputField("Amount",
                value = membersContribution,
                onValueChange = {
                    membersContribution = it
                },
                acceptNumericalData = true,
                modifier = Modifier.width(85.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Emerging Issues", color = textColor, fontSize = 16.sp)
        TextField(
            value = emergingIssues,
            onValueChange = {
                emergingIssues = it
            },
            placeholder = {
                Text(text = "Emerging issues", color = Color.LightGray, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = {

            }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                disabledContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier.height(250.dp).fillMaxWidth()
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB7CE)
            ),
            shape = RoundedCornerShape(19.dp),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save data", fontSize = 16.sp, color = Color.White)
        }

    }
}