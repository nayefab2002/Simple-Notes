package com.nayef.simplenote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nayef.simplenote.data.NavigationItem
import com.nayef.simplenote.ui.navigation.NavigationRoutes
import com.nayef.simplenote.ui.theme.poppins


val navigationItems= arrayOf(
    NavigationItem(
        title = "Active",
        imageVector = Icons.Default.List,
        route = NavigationRoutes.Active.route,
        selected = true,
        onClick = {}
    ),
    NavigationItem(
        title = "Trash",
        imageVector = Icons.Default.Delete,
        route = NavigationRoutes.Trash.route,
        selected = false,
        onClick = { }
    )
)
@Composable
fun BottomNavigationBar(navController: NavController, currentScreen:Boolean){
    var selectedNavigationIndex by rememberSaveable { mutableIntStateOf(0) }

    Box() {
        NavigationBar(modifier = Modifier
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))) {
            navigationItems.forEachIndexed({index, item->
                NavigationBarItem(
                    selected = index==selectedNavigationIndex,
                    onClick = {
                        //navController.navigate(item.route)
                        selectedNavigationIndex=index
                    },
                    icon = {
                        Icon(imageVector = item.imageVector, contentDescription = "",modifier = Modifier.size(24.dp))
                    },
                    modifier = Modifier.padding(vertical = 0.dp, horizontal = 0.dp),
                    label = {
                        Text(item.title, color = if(index==selectedNavigationIndex) Color.Green else Color.Gray, style = TextStyle(fontFamily = poppins))
                    },
                    //alwaysShowLabel = TODO(),
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    //interactionSource = TODO()
                )
            })

        }
    }

}

@Preview
@Composable
fun BottomNavigationBarPreview(){
    val navController= rememberNavController()
    BottomNavigationBar(navController,currentScreen = true)

}