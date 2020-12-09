package com.ulp.ym.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulp.ym.util.Util
import com.ulp.ym.data.Item
import com.ulp.ym.data.Users
import com.ulp.ym.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class UsersViewModel : ViewModel() {

    private var users: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()

    fun getUsers(): LiveData<List<Item>> {
        return users
    }

    fun loadUsers(searchTxt: String?) {
        // Do an asynchronous operation to fetch users.
        GlobalScope.launch(Dispatchers.IO) {
            val result = Api.httpGet(Util.GITHUB_USERS.plus(searchTxt))

            val obj: Users = Json.decodeFromString(Users.serializer(), result)

            users.postValue(obj.items)
        }
    }
}