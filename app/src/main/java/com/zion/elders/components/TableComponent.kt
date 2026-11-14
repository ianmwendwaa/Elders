package com.zion.elders.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.core.dto.TableModel
import kotlin.collections.forEach

@Composable
fun <T: TableModel> TableComponent(
    columnHeadLabels: List<String>,
    rowContent: List<T>,
    rowCellFactory: (T) -> List<String>
){
    Card(
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4F4F4)
        ),
        modifier = Modifier.border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(5.dp)) {
            // Headers
            Row(modifier = Modifier.fillMaxWidth().padding(start = 5.dp)) {
                columnHeadLabels.forEach { label ->
                    Text(text = label,
                        fontSize = 14.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f).padding(5.dp))
                }
            }
            // Cells
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                // Draw each cell
                rowContent.forEach { item ->
                    Row(modifier = Modifier.fillMaxWidth().border(0.01.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))){
                        val cells = rowCellFactory(item)

                        cells.forEach { cellText ->
                            Text(text = cellText, fontSize = 15.sp,
                                modifier = Modifier.weight(1f).padding(12.dp))
                        }
                    }
                }
            }
        }
    }
}