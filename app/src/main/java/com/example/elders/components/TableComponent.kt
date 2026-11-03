package com.example.elders.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elders.models.TableModel
import kotlin.collections.forEach

@Composable
fun <T: TableModel> TableComponent(
    columnHeadLabels: List<String>,
    rowContent: List<T>,
    rowCellFactory: (T) -> List<String>
){
    Column {
        Row(modifier = Modifier.fillMaxWidth().border(0.5.dp, color = Color.LightGray)) {
            columnHeadLabels.forEach { label ->
                Text(text = label, modifier = Modifier.weight(1f).padding(5.dp))
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .border(0.5.dp, color = Color.Gray)
        ) {
            // Draw each cell
            rowContent.forEach { item ->
                Row(modifier = Modifier.fillMaxWidth().border(0.5.dp, color = Color.Gray)){
                    val cells = rowCellFactory(item)

                    cells.forEach { cellText ->
                        Text(text = cellText, fontSize = 16.sp,
                            modifier = Modifier.weight(1f).padding(12.dp))
                    }
                }
            }
        }
    }
}