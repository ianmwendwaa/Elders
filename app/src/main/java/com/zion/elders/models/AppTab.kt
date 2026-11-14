package com.zion.elders.models

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.zion.elders.R

enum class AppTab(
    val label: String,
    val filledIcon: Int,
    val outlinedIcon: Int
) {
    HOME("Home", R.drawable.home_filled, R.drawable.home_outline),
    MEETINGS("Meetings", R.drawable.trending_up_filled, R.drawable.trending_up_filled)
}