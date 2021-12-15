package com.maneesh.reqresandroid.composables

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maneesh.reqresandroid.Routes

class Composables{

    companion object{
        @Composable
        fun CustomTopAppBar(title:String,state:Int,navController: NavController){
            TopAppBar(title = { Text(text = title)}, backgroundColor = MaterialTheme.colors.primaryVariant,
            navigationIcon = {
                if(state!=0){
                    IconButton(onClick = {navController.navigateUp()}) {
                        Image(imageVector = Icons.Outlined.ArrowBack, contentDescription ="Back arraow", colorFilter = ColorFilter.tint(
                            Color.White))
                    }
                }
            }
                )
        }
    }
}