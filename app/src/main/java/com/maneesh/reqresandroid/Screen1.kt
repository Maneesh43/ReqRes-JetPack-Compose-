package com.maneesh.reqresandroid

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Screen1 {
    @Composable
    fun FirstScreen(mainViewModel: MainViewModel) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "FirstScreen") {
            composable("FirstScreen") {
                ShowUsers(list = mainViewModel.userList)
                mainViewModel.getUsers()
            }
            composable("UserDetails"){
                val id=it.arguments?.getString("id")
                Screen2().showUserDetails(id = id!!, viewModel =mainViewModel )
            }
        }
    }

}