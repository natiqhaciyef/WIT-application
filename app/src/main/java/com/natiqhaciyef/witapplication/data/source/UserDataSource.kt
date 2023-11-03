package com.natiqhaciyef.witapplication.data.source

import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.network.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource(
    private val service: UserService
){

    suspend fun getAllUsers() = withContext(Dispatchers.IO){
        service.getAllUsers()
    }

    suspend fun getUserByEmail(email: String) = withContext(Dispatchers.IO){
        service.getUserByEmail(email = email)
    }

    suspend fun insertUser(userModel: UserModel) = withContext(Dispatchers.IO){
        service.insertUser(
            name = userModel.name,
            email = userModel.email,
            password = userModel.password,
        )
    }

    suspend fun updateUser(userModel: UserModel) = withContext(Dispatchers.IO){
        service.updateUser(
            id = userModel.id,
            name = userModel.name,
            email = userModel.email,
            password = userModel.password,
        )
    }

    suspend fun removeUser(id: Int) = withContext(Dispatchers.IO){
        service.deleteUser(id = id)
    }

}
