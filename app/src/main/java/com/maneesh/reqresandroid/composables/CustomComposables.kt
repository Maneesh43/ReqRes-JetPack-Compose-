package com.maneesh.reqresandroid.composables

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.maneesh.reqresandroid.MainActivity
import com.maneesh.reqresandroid.ui.theme.TangyOrange

class CustomComposables {

    companion object {
        @Composable
        fun CustomTopAppBar(
            title: String,
            state: Int,
            navController: NavController,
            clickState: Int=0
        ) {
            val context = LocalContext.current
            TopAppBar(title = { Text(text = title, color = Color.White) },
                backgroundColor = TangyOrange,
                navigationIcon = {
                    if (state != 0) {
                        IconButton(onClick = {
                            if (clickState == 1) {
                                Intent(
                                    context,
                                    MainActivity::class.java
                                ).also{it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)}.also { context.startActivity(it) }
                            } else {
                                navController.navigateUp()
                            }
                        }) {
                            Image(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back arraow",
                                colorFilter = ColorFilter.tint(
                                    Color.White
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}