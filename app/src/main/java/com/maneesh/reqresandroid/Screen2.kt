package com.maneesh.reqresandroid

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.maneesh.reqresandroid.composables.CustomComposables
import com.maneesh.reqresandroid.network.models.UserClass
import com.maneesh.reqresandroid.ui.theme.ReqresandroidTheme

class Screen2 {

    @Composable
    fun ShowUserDetails(id: String, viewModel: MainViewModel, navcontroller: NavController) {
        ShowUser(viewModel.userWithID, navcontroller)
        viewModel.getUserID(id)
    }

//This composable is responsible for showing individual user information

    @Composable
    fun ShowUser(item: UserClass, navController: NavController) {

//        Handling back pressed in Back Handler and calling navigation compose navigateUp() to move to previous screen
        BackHandler(enabled = true) {
            navController.navigateUp()
        }
//        Scaffold to contain topabbbar and app Content
        Scaffold(
            topBar = {CustomComposables.CustomTopAppBar("${item.firstName} ${item.lastName}",1, navController = navController) },
            content = {
            if (item.id.isNotEmpty()) {
                Surface() {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)) {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Image(
                                rememberImagePainter(item.avatar),
                                contentDescription = "${item.firstName} image",
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                            )
                        }

                        Row(modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            Text(text = item.firstName, fontWeight = FontWeight.Bold)
                            Text(text = item.lastName, fontWeight = FontWeight.Bold)
                        }

                        Column(modifier= Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = item.email)
                        }

                    }
                }
            }
        })

    }
//


//    Preview Composable for quickly looking at the layout
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        ReqresandroidTheme {
            ShowUser(item = UserClass("1","thouti.maneesh","Maneesh","Reddy","https://reqres.in/img/faces/1-image.jpg"), navController = rememberNavController() )
        }
    }
}