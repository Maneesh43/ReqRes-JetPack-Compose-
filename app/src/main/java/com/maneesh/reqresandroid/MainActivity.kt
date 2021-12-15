package com.maneesh.reqresandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.reqres.network.models.UserClass
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


@Composable
fun showSingleUser(){
    
}

@Composable
fun ShowUsers(list: List<UserClass>) {
    if (list.isNotEmpty()) {
        LazyColumn{
            itemsIndexed(items=list){index, item ->
                UserCard(useritem = item)
            }
        }
    }
}





@Composable
fun UserCard(useritem: UserClass) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        androidx.compose.material.Card(elevation = 5.dp, modifier = Modifier
            .padding(8.dp)
            .clickable { } ) {
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
                    Text(text = useritem.lastName,modifier=Modifier.padding(end=8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReqresandroidTheme {
        UserCard(useritem = UserClass("1", "Maneesh", "Reddy", "Regg", "https://www.google.ca"))
    }
}