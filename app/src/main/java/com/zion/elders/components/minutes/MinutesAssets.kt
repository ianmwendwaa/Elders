package com.zion.elders.components.minutes

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.components.cardColor
import com.zion.elders.components.pillBackgroundColor
import com.zion.elders.components.pillButtonColor
import com.zion.elders.components.systemFormColor
import com.zion.elders.models.EditorMode
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.previewBoxColor


@Composable
fun PillButton(
    selectedMode: EditorMode,
    onActiveModeChanged: (EditorMode) -> Unit,
    pillWidth: Dp = 60.dp
){
    // Smoothly animate horizontal slide offset
    val animatedOffset by animateDpAsState(
        targetValue = pillWidth * selectedMode.ordinal,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "pill_slide_offset"
    )
    Box(modifier = Modifier.clip(RoundedCornerShape(70.dp)).background(
        pillBackgroundColor).padding(5.dp)
    ){
        // Sliding pill
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .width(pillWidth)
                .height(36.dp)
                .padding(end = 5.dp)
                .clip(RoundedCornerShape(70.dp))
                .background(pillButtonColor)
        )
        Row {
            EditorMode.entries.forEach { mode ->
                val is_selected = selectedMode == mode

                Box(
                    modifier = Modifier.width(pillWidth).height(36.dp)
                        .clickable(
                            enabled = true,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onActiveModeChanged(mode) }),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = mode.label, color = if (is_selected) Color.White else Color.Black,
                        fontSize = 14.sp, fontWeight = if (is_selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun TextFormattingFeature(
    formattingIcon: Int,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier.size(30.dp).clip(RoundedCornerShape(8.dp))
            .background(cardColor).border(1.dp, shape = RoundedCornerShape(8.dp),
                color = Color.Gray).clickable(true, onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(formattingIcon),
            contentDescription = "formatting_feature_icon",
            modifier = Modifier.size(15.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun CustomTextFieldForm(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){
    Card(modifier = modifier.padding(start = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = systemFormColor
        )
    ) {
        Column(modifier = Modifier.padding(8.dp), content = content)
    }
}


@Composable
fun FullPreviewLabel(){
    Box(
        modifier = Modifier.width(95.dp).height(35.dp).clip(RoundedCornerShape(13.dp))
            .background(previewBoxColor),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.fillMaxWidth().padding(3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Circle
            Box(modifier = Modifier.size(15.dp).clip(RoundedCornerShape(80.dp)).background(
                experimentalBackground
            ))
            Text(text = "Preview", fontSize = 13.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}