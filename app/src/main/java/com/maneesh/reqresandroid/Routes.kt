package com.maneesh.reqresandroid


sealed class Routes(val route:String){
    object UsersView : Routes("usersview")
    object UserView : Routes("userview")
}
