package com.nayef.simplenote.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import com.nayef.simplenote.ui.theme.poppins

@Composable
fun SimpleNotesTopBar(title: String) {
    Text(
        text = title,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp, fontFamily = poppins),
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 15.dp)
    )
}