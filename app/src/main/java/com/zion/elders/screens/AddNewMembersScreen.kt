package com.zion.elders.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zion.elders.components.DataInputField
import com.zion.elders.components.backgroundColor
import com.zion.elders.styles.HeadingText

@Composable
fun AddNewMembersScreen(navController: NavHostController){
    var newMemberName by remember { mutableStateOf("") }
    var phoneNumberTxtEditState by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(start = 20.dp, top = 35.dp, end = 20.dp, bottom = 20.dp)
        .background(backgroundColor)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                HeadingText("Add Member")

                DataInputField("Member's Name",
                    value = newMemberName,
                    onValueChange = {
                        newMemberName = it
                    }
                )
                DataInputField("Member's Phone Number",
                    value = phoneNumberTxtEditState,
                    onValueChange = {
                        phoneNumberTxtEditState = it
                    },
                    acceptNumericalData = true
                )

                Button(onClick = {
                    d(context, newMemberName, phoneNumberTxtEditState, navController)
                },
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFB7CE)
                    ),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Save member", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}

private fun d(context: Context, name: String, phoneNumber: String, navController: NavHostController){
    if (name.isEmpty() && phoneNumber.isEmpty()) return
    Toast.makeText(context, "Member $name added! ", Toast.LENGTH_SHORT).show()
    navController.popBackStack()
}