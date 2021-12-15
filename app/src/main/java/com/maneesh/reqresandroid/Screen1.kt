package com.maneesh.reqresandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.maneesh.reqresandroid.composables.CustomComposables
import com.maneesh.reqresandroid.network.models.UserClass

class Screen1 {
    @Composable
    fun FirstScreen(mainViewModel: MainViewModel) {

//        Instantiating navController and setting up the NavHost for moving between different composables
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.UsersView.route) {
            composable(Routes.UsersView.route) {
                ShowUsers(list = mainViewModel.userList, navController = navController)
                mainViewModel.getUsers()
            }
            composable(Routes.UserView.route + "/{id}") { navBackStack ->
                val id = navBackStack.arguments?.getString("id")
                Screen2().ShowUserDetails(id = id!!, viewModel = mainViewModel, navController)
            }
        }
    }
//


    @Composable
    fun ShowUsers(list: List<UserClass>, navController: NavController) {

//        Using Scaffold to contain topAppBar
        Scaffold(
            topBar = {
                CustomComposables.CustomTopAppBar(
                    "ReqRes Android",
                    0,
                    navController = navController
                )
            },

            content = {
                if (list.isNotEmpty()) {
//                    Using Lazycolumn for iterating over the list this is equivalent to RecyclerView
//    itemsIndexed is a special type of lazy column which gives us access to the index
                    LazyColumn {
                        itemsIndexed(items = list) { index, item ->
                            UserCard(useritem = item, navController)
                        }
                    }
                }
            }
        )

    }


    @Composable
    fun UserCard(useritem: UserClass, navController: NavController) {
//        Basic Card Layout in compose
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            androidx.compose.material.Card(elevation = 4.dp, modifier = Modifier
                .padding(4.dp)
                .clickable { navController.navigate(Routes.UserView.route + "/${useritem.id}") }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = rememberImagePainter(useritem.avatar),
                        contentDescription = "${useritem.firstName} image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                    )

                    Column(
                        modifier = Modifier
                            .size(100.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = useritem.firstName, modifier = Modifier.padding(end = 8.dp))
                        Text(text = useritem.lastName, modifier = Modifier.padding(end = 8.dp))
                    }
                }
            }
        }
    }

}