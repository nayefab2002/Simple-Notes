package com.nayef.simplenote.ui.navigation

sealed class NavigationRoutes(val route:String) {
    data object Active: NavigationRoutes("/active")
    data object Trash:NavigationRoutes(route = "/trash")
}