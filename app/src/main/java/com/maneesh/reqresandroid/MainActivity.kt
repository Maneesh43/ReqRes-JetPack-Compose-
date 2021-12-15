package com.maneesh.reqresandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.maneesh.reqresandroid.network.models.UserClass
import com.maneesh.reqresandroid.ui.theme.ReqresandroidTheme

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReqresandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   Screen1().FirstScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReqresandroidTheme {
        Screen1().UserCard(useritem = UserClass("1", "Maneesh", "Reddy", "Regg", "https://www.google.ca"), navController = rememberNavController())
    }
}