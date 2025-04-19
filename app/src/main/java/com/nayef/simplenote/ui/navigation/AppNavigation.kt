package com.nayef.simplenote.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nayef.simplenote.data.NotesViewModel
import com.nayef.simplenote.ui.screens.NoteListScreen

@Composable
fun AppNavigation(viewModel: NotesViewModel){
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = "list"){
        composable("list"){ NoteListScreen(viewModel = viewModel, navController = navController)}
//        composable("detail/{noteId}"){
//            backStackEntry ->
//                val noteId=backStackEntry.arguments?.getString("noteId")?.toInt()?:0
//
//
//        }
    }
}