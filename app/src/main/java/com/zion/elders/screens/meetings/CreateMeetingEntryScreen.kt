package com.zion.elders.screens.meetings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zion.elders.R
import com.zion.elders.components.DatePickerComponent
import com.zion.elders.components.SystemForm
import com.zion.elders.components.backgroundColor
import com.zion.elders.components.cardColor
import com.zion.elders.navigation.ROUTE_MINUTES_SCREEN
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.moneyBoxColor
import com.zion.elders.ui.theme.moneyTextColor
import com.zion.elders.ui.theme.screenBackground
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private data class ContributionData(
    val contributorsName: String,
    val contributionAmt: Int
)
@Composable
fun LogMeetingDataScreen(navController: NavHostController){
    val dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    var showDatePicker by remember { mutableStateOf(false) }
    var datePicked by remember { mutableStateOf<Long?>(null) }
    var meetingTitle by remember { mutableStateOf("") }
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    val contributionsList = remember { mutableStateListOf<ContributionData>() }
    val totalContribution by remember {
        derivedStateOf {
            contributionsList.sumOf { it.contributionAmt }
        }
    }
   Column(
       modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
           .background(backgroundColor),
       verticalArrangement = Arrangement.spacedBy(3.dp)
   ) {
       // Meeting preliminary data (date, title)
       SystemForm(
           modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 40.dp).fillMaxWidth()
       ) {
           Text(text = "Log New Meeting Data", color = cardTitle, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
           Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
           Text(text = "Meeting date", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)

           Box(modifier = Modifier.fillMaxWidth().height(50.dp)){
               TextField(
                   value = "",
                   onValueChange = { },
                   readOnly = true,
                   enabled = false,
                   placeholder = {
                       Text(text = datePicked.toString(), fontSize = 14.sp)
                   },
                   trailingIcon = {
                       Icon(painterResource(R.drawable.date_edit_field_trailing_icon), contentDescription = "trailing_icon", tint = Color.Gray)
                   },
                   shape = RoundedCornerShape(7.dp),
                   colors = TextFieldDefaults.colors(
                       disabledContainerColor = cardColor,
                       disabledPlaceholderColor = Color.LightGray,
                   ),
                   modifier = Modifier.fillMaxSize().padding(10.dp)
               )
               Box(modifier = Modifier.matchParentSize().clickable(true, onClick = { showDatePicker = true }))
           }

           if (showDatePicker) {
               DatePickerComponent(
                   onDateSelected = { datePicked = it },
                   onDismiss = { showDatePicker = false }
               )
           }
           Text(text = "Meeting Title", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)

           TextField(
               value = meetingTitle,
               onValueChange = {
                   meetingTitle = it
               },
               placeholder = {
                   Text(text = "Meeting title", fontSize = 14.sp)
               },
               shape = RoundedCornerShape(7.dp),
               colors = TextFieldDefaults.colors(
                   focusedContainerColor = Color.White,
                   unfocusedContainerColor = Color.White,
                   disabledContainerColor = Color.White,
                   focusedIndicatorColor = Color.Transparent,
                   unfocusedIndicatorColor = Color.Transparent
               ),
               textStyle = TextStyle(
                   fontSize = 14.sp,
                   color = Color.Black
               ),
               modifier = Modifier.height(50.dp),
               singleLine = true,
               keyboardOptions = KeyboardOptions(
                   capitalization = KeyboardCapitalization.Words,
                   imeAction = ImeAction.Done
               )
           )
       }

       // Append contributions' form
       SystemForm(
           modifier = Modifier.padding(start = 16.dp, end = 16.dp)
       ) {
           Text(text = "Append Contributions", fontSize = 14.sp, color = cardTitle, fontWeight = FontWeight.SemiBold)
           Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

           Text("Select member", fontSize = 13.sp, color = Color.Gray)
           TextField(
               value = "",
               onValueChange = { },
               readOnly = true,
               placeholder = {
                   Text(text = "Ian Mwendwa (ELDR-IANM-0433)", fontSize = 14.sp)
               },
               trailingIcon = {
                    Icon(painterResource(R.drawable.arrow_drop_down), contentDescription = null, tint = Color.Gray)
               },
               shape = RoundedCornerShape(7.dp),
               colors = TextFieldDefaults.colors(
                   focusedContainerColor = Color.White,
                   unfocusedContainerColor = Color.White,
                   disabledContainerColor = Color.White,
                   focusedIndicatorColor = Color.Transparent,
                   unfocusedIndicatorColor = Color.Transparent
               ),
               modifier = Modifier.height(48.dp)
           )
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text("Contribution", fontSize = 13.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
               Text(text = "Ksh.${sliderValue.toInt()}", fontSize = 13.sp, color = moneyTextColor, fontWeight = FontWeight.SemiBold)
           }

           // @ToDo: Proposal: Should this be swapped with a text field? It's low-key difficult to deal with
           Slider(
               value = sliderValue,
               onValueChange = {
                   sliderValue = it
               },
               valueRange = 50f..200f,
               // ((max - min)/desired_steps (eg. 10))-1
               steps = 2,
               colors = SliderDefaults.colors(
                   thumbColor = experimentalBackground,
                   activeTickColor = cardColor,
                   activeTrackColor = experimentalBackground,
                   inactiveTickColor = experimentalBackground
               )
           )
           Button(
               onClick = {
                   appendContribution(contributionsList, "Ian Mwendwa", (sliderValue).toInt())
               },
               shape = RoundedCornerShape(8.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = experimentalBackground
               ),
               modifier = Modifier.align(Alignment.End)
           ) {
               Text(text = "Push Contribution", color = Color.White, fontSize = 13.sp)
           }
       }
       // Appended contributions list
       SystemForm(
           modifier = Modifier.padding(start = 16.dp, end = 16.dp)
       ) {
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(text = "Contributions", color = cardTitle, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
               Text(text = "Total: Ksh.$totalContribution", color = moneyTextColor, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
           }
           Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

           // Member data
           if (contributionsList.isEmpty()){
               Text(text = "No contributions pushed yet.", fontStyle = FontStyle.Italic, fontSize = 13.sp, color = Color.Gray)
           } else {
               contributionsList.forEach { (contributorsName, contributionAmt) ->
                   ContributionsListItem(contributorsName, contributionAmt)
               }
           }
       }

       SystemForm(
           modifier = Modifier.padding(start = 16.dp, end = 16.dp)
       ) {
           Text(text = "Meeting Minutes", fontWeight = FontWeight.SemiBold, color = cardTitle, fontSize = 14.sp)
           Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

           Box(
               modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Transparent)
           ){
               TextField(
                   value = "",
                   onValueChange = { },
                   readOnly = true,
                   enabled = false,
                   placeholder = {
                       Text(text = "Click to record meeting minutes", fontSize = 13.sp)
                   },
                   modifier = Modifier.fillMaxSize()
                       .clickable(true, onClick = { navController.navigate(ROUTE_MINUTES_SCREEN) }),
                   colors = TextFieldDefaults.colors(
                       disabledContainerColor = cardColor,
                       disabledPlaceholderColor = Color.Gray
                   ),
                   shape = RoundedCornerShape(7.dp)
               )
               Box(
                   modifier = Modifier.matchParentSize()
                       .clip(RoundedCornerShape(7.dp))
                       .clickable(enabled = true){
                           navController.navigate(ROUTE_MINUTES_SCREEN)
                       }
               )
           }
       }
       Button(
           onClick = {},
           shape = RoundedCornerShape(8.dp),
           colors = ButtonDefaults.buttonColors(
               containerColor = experimentalBackground
           ),
           modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp).fillMaxWidth().height(40.dp)
       ) {
           Text(text = "Save meeting data", fontSize = 14.sp, color = Color.White)}
   }
}

private fun appendContribution(lst: MutableList<ContributionData>, name: String, amt: Int){
    if (name.isEmpty() && amt.toString().isEmpty()) return
    lst.add(ContributionData(name, amt))
}

@Composable
fun ContributionsListItem(
    contributorsName: String,
    contributionAmt: Int
){
    // Yes, I know I named it contributee
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(contributorsName, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Text(text = "Ksh.$contributionAmt", fontWeight = FontWeight.SemiBold, color = Color(0xFF50C878))
    }
}

@Preview
@Composable
fun CreateMeetingPreview(){
    LogMeetingDataScreen(rememberNavController())
}
