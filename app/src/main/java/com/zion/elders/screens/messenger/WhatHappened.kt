package com.zion.elders.screens.messenger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.R
import com.zion.elders.components.systemFormColor
import com.zion.elders.models.MessageType
import com.zion.elders.models.ResultData
import com.zion.elders.ui.theme.cardTitle

@Composable
fun WhatHappened(
    resultData: ResultData
){
    val messageType = resultData.messageType
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.height(500.dp).padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(systemFormColor),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(50.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(if (messageType == MessageType.SUCCESS) Color.Green else Color.Red
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(if (messageType == MessageType.SUCCESS) R.drawable.ic_success else R.drawable.ic_error),
                        contentDescription = "what_happened_img",
                        tint = Color.White
                    )
                }
                Text(
                    text = resultData.resultTitle, fontSize = 21.sp, fontWeight = FontWeight.Bold,
                    color = if (messageType == MessageType.SUCCESS) cardTitle else Color.Red
                )
                Text(
                    text = resultData.resultDescription, fontSize = 16.sp, fontWeight = FontWeight.Normal,
                    color = Color.Black, textAlign = TextAlign.Center
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { }
                ) { }
            }
        }
    }
}

@Preview
@Composable
fun P(){
    WhatHappened(
        ResultData(MessageType.SUCCESS, "Member added!", "Successfully added new member to the system!")
    )
}