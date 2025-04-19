package com.nayef.simplenote.data

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(val title:String, val imageVector: ImageVector, val route:String,val selected:Boolean, val onClick:()->Unit)
