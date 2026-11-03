// Createed by Mwendwa on 03/11/2026

package com.example.elders.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.elders.navigation.ROUTE_HOME_SCREEN
import com.example.elders.styles.HeadingText

@Composable
fun WelcomeScreen(
    navController: NavHostController
){
    var txtEditState by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.width(100.dp).height(100.dp)
                .clip(RoundedCornerShape(200.dp))
                .background(Color(0xFFFFB7CE))

        )
        Spacer(modifier = Modifier.height(10.dp))
        HeadingText("Welcome! What is your name?")

        TextField(
            value = txtEditState,
            onValueChange = {
                txtEditState = it
            },
            placeholder = {
                Text(text = "Enter your name", color = Color.LightGray, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
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
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = { navController.navigate(ROUTE_HOME_SCREEN) },
            modifier = Modifier.width(120.dp).height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB7CE),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(13.dp)
        ) {
            Text(text = "Log in", textAlign = TextAlign.Center, fontSize = 17.sp, color = Color.White)
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview(){
//    WelcomeScreen()
}