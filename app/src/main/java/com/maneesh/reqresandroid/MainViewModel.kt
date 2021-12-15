package com.maneesh.reqresandroid

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maneesh.reqresandroid.network.APIService
import com.maneesh.reqresandroid.network.models.UserClass
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var userList: List<UserClass> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var userWithID: UserClass by mutableStateOf(UserClass())

    fun getUsers() {
//        Launching Co-Routine to get the data.
        viewModelScope.launch {
            val service = APIService.getInstance()
            try {
                val users = service.getAllUsers().data
                userList = users
                Log.d("hello", userList[0].firstName)
            } catch (e: CancellationException) {
//                Propagating Cancellation Exception otherwise coRoutine will exit with CoRoutineCancelled Exception
                throw e
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getUserID(id: String) {
        viewModelScope.launch {
            val service = APIService.getInstance()
            try {
                val user = service.getUser(id).data
                userWithID = user
            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}