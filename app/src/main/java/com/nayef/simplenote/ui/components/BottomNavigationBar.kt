package com.nayef.simplenote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nayef.simplenote.ui.navigation.NavigationRoutes
import com.nayef.simplenote.ui.theme.poppins

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun BottomNavigationBar(navController: NavController, currentScreen: Boolean) {
    val navItems = listOf(
        BottomNavItem("Active", Icons.Default.List, NavigationRoutes.Active.route),
        BottomNavItem("Trash", Icons.Default.Delete, NavigationRoutes.Trash.route)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    Box {
        NavigationBar(
            modifier = Modifier.clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            navItems.forEach { item ->
                val selected = currentRoute == item.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.navigate(item.route) {
                                popUpTo(NavigationRoutes.Active.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    },
                    label = {
                        Text(
                            item.title,
                            style = TextStyle(fontFamily = poppins)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}
