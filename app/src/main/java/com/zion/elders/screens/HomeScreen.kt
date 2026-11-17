package com.zion.elders.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zion.elders.R
import com.zion.elders.backend.Lib
import com.zion.elders.components.MeetingDataCard
import com.zion.elders.components.OverviewDataCard
import com.zion.elders.components.SystemForm
import com.zion.elders.models.MeetingContributionsBreakdown
import com.zion.elders.models.MeetingData
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.disabledButtonColor
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.viewmodels.HomeOverviewViewModel
import com.zion.elders.viewmodels.QuickRegistrationViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    navController: NavController,
    registrationViewModel: QuickRegistrationViewModel = viewModel(),
    overviewDataViewModel: HomeOverviewViewModel = viewModel()
){
    val overviewDataState by overviewDataViewModel.uiState.collectAsStateWithLifecycle()
    val registrationFormState by registrationViewModel.uiState.collectAsStateWithLifecycle()
    val totalMembers by remember { mutableIntStateOf(overviewDataViewModel.getTotalMembers()) }
    val totalContribution by remember { mutableIntStateOf(overviewDataViewModel.getNetContributions() ) }

    val context = LocalContext.current

    val dummyMeetingData = MeetingData(
        "First meeting",
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        750,
        listOf(
            MeetingContributionsBreakdown("Ian Mwendwa", 50),
            MeetingContributionsBreakdown("Ian Mwendwa", 50),
            MeetingContributionsBreakdown("Ian Mwendwa", 50)
        ),
        "Some emerging issues as discussed"
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        Text(text = "Hello Mwanazayuni!", fontSize = 19.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp),
            color = Color.Black
        )

        // Overview data
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            OverviewDataCard(
                "Total Roster",
                R.drawable.group_filled,
                totalMembers.toString(),
                "Verified Active"
            )

            OverviewDataCard(
                "Contributions",
                R.drawable.money_bag,
                "Ksh.$totalContribution",
                "Net contributions"
            )

        }

        // Quick Register Member
        SystemForm(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.ic_right_button), "quick_reg_mem")
                Text(text = "Quick Member Registration", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = cardTitle)
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))

            TextField(
                value = registrationFormState.fullName,
                onValueChange = {
                    registrationViewModel.onFullNameChanged(it)
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                placeholder = {
                    Text("Full name", fontSize = 14.sp)
                },
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(7.dp)
            )

            TextField(
                value = registrationFormState.phoneNumber,
                onValueChange = {
                    registrationViewModel.onPhoneNumberChanged(it, keyboardController, focusManager)
                },
                modifier = Modifier.width(150.dp).height(48.dp),
                placeholder = {
                    Text("Phone No.", fontSize = 14.sp)
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberSigned,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.Transparent, // Hides border on focus
                    unfocusedIndicatorColor = Color.Transparent, // Hides border when idle
                ),
                shape = RoundedCornerShape(7.dp)
            )

            Button(onClick = {
                registrationViewModel.onRegisterMember(context)
            },
                modifier = Modifier.width(100.dp).height(45.dp).align(Alignment.End),
                shape = RoundedCornerShape(10.dp),
                enabled = registrationFormState.phoneNumber.isNotEmpty() && registrationFormState.fullName.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = experimentalBackground,
                    disabledContainerColor = disabledButtonColor
                )
            ) {
                Text(text = "Register", color = Color.White, fontSize = 14.sp)
            }
        }

        // Recent meetings
        Row(modifier = Modifier.fillMaxWidth().padding(
            start = 16.dp, end = 16.dp, bottom = 1.dp, top = 10.dp
        ), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Recent meetings", color = cardTitle, fontWeight = FontWeight.SemiBold)
            Text(text = "See all", color = experimentalBackground, fontSize = 13.5.sp)
        }
        MeetingDataCard(dummyMeetingData)
    }
}