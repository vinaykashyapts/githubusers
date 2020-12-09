package com.ulp.ym.ui.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulp.ym.data.Item
import com.ulp.ym.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FollowersViewModel : ViewModel() {

    private var users: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()

    fun getFollowers(): LiveData<List<Item>> {
        return users
    }

    fun loadFollowers(url: String?) {
        // Do an asynchronous operation to fetch users.
        GlobalScope.launch(Dispatchers.IO) {
            val result = Api.httpGet(url)

            val obj: List<Item> = Json.decodeFromString(result)

            users.postValue(obj)
        }
    }
}