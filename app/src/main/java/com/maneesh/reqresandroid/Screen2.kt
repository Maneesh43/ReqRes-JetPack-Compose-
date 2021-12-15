package com.maneesh.reqresandroid

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.reqres.network.models.UserClass

class Screen2 {

    @Composable
    fun showUserDetails(id:String,viewModel: MainViewModel){
       ShowUser(viewModel.userWithID)
        viewModel.getUserID(id)
    }


    @Composable
    fun ShowUser(item:UserClass){
        if(item.id.isNotEmpty()){
            Surface(){

            }
        }
    }

}