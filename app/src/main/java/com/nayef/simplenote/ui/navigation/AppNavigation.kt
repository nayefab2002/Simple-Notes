package com.nayef.simplenote.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nayef.simplenote.data.NotesViewModel
import com.nayef.simplenote.ui.screens.ActiveScreen
import com.nayef.simplenote.ui.screens.TrashScreen

@Composable
fun AppNavigation(viewModel: NotesViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoutes.Active.route) {
        composable(NavigationRoutes.Active.route) {
            ActiveScreen(viewModel = viewModel, navController = navController)
        }
        composable(NavigationRoutes.Trash.route) {
            TrashScreen(viewModel = viewModel, navController = navController)
        }
    }
}
