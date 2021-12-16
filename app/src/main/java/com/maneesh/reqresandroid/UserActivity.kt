package com.maneesh.reqresandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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

class UserActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.extras!!.getString("id")!!
        super.onCreate(savedInstanceState)
        setContent {
            ReqresandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShowUser(mainViewModel.userWithID)
                    mainViewModel.getUserID(id)
                }
            }
        }
    }
}

@Composable
fun ShowUser(item: UserClass) {
    val navController = rememberNavController()
//        Handling back pressed in Back Handler and calling navigation compose navigateUp() to move to previous screen
    BackHandler(enabled = true) {
        navController.navigateUp()
    }
//        Scaffold to contain topabbbar and app Content
    Scaffold(
        topBar = {
            CustomComposables.CustomTopAppBar(
                "${item.firstName} ${item.lastName}",
                state = 1,
                navController = navController,
                clickState = 1
            )
        },
        content = {
            if (item.id.isNotEmpty()) {
                Surface() {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                rememberImagePainter(item.avatar),
                                contentDescription = "${item.firstName} image",
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = item.firstName, fontWeight = FontWeight.Bold)
                            Text(text = item.lastName, fontWeight = FontWeight.Bold)
                        }

                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = item.email)
                        }

                    }
                }
            }
        })

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ReqresandroidTheme {

    }
}