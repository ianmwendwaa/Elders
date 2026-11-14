package com.zion.elders.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeActionsCard(
    cardActionImage: Int,
    cardActionLabel: String,
    onActionCardClicked: ()-> Unit
){
    Card(
        modifier = Modifier.width(150.dp).height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4F4F4)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = onActionCardClicked
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(13.dp)) {
            Box(modifier = Modifier.size(width = 40.dp, height = 40.dp).clip(RoundedCornerShape(100.dp)).background(Color.LightGray)
            ){
                Image(
                    painter = painterResource(cardActionImage),
                    contentDescription = "Action image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(text = cardActionLabel, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)

        }

    }
}