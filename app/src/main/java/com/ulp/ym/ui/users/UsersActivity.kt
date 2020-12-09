package com.ulp.ym.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.ulp.ym.interfaces.ItemClickListener
import com.ulp.ym.R
import com.ulp.ym.data.Item
import com.ulp.ym.ui.followers.FollowersActivity
import com.ulp.ym.util.Util.toast

class UsersActivity : AppCompatActivity(), ItemClickListener {

    lateinit var recyclerViewAdapter: UsersAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        supportActionBar?.setTitle("Github Users")

        initRecyclerView()

        val model = ViewModelProvider(this).get(UsersViewModel::class.java)

        model.getUsers().observe(this, { users ->
            // update UI
            recyclerViewAdapter.setListData(users as ArrayList<Item>)
            recyclerViewAdapter.notifyDataSetChanged()
        })

        val searchTxt: TextInputEditText = findViewById(R.id.searchUserInput)

        val searchBtn: Button = findViewById(R.id.searchButton)
        searchBtn.setOnClickListener {
            var txt:String = searchTxt.text.toString()
            if(txt.isNullOrEmpty()) toast("Enter user name") else model.loadUsers(txt)
        }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@UsersActivity)
            recyclerViewAdapter = UsersAdapter(this@UsersActivity, this@UsersActivity)
            adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(item: Item) {
        Intent(this, FollowersActivity::class.java).also {
            it.putExtra("user", item.login)
            it.putExtra("followers_url", item.followers_url)
            startActivity(it)
        }
    }
}