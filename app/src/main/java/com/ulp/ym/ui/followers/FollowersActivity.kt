package com.ulp.ym.ui.followers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulp.ym.R
import com.ulp.ym.data.Item
import com.ulp.ym.interfaces.ItemClickListener
import com.ulp.ym.ui.users.UsersAdapter

class FollowersActivity : AppCompatActivity(), ItemClickListener {

    lateinit var recyclerViewAdapter: UsersAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followers)

        processIntent()

        initRecyclerView()

        val model = ViewModelProvider(this).get(FollowersViewModel::class.java)

        model.getFollowers().observe(this, { users ->
            // update UI
            recyclerViewAdapter.setListData(users as ArrayList<Item>)
            recyclerViewAdapter.notifyDataSetChanged()
        })

        model.loadFollowers(url)
    }

    private fun processIntent() {

        var user = intent.getStringExtra("user")
        url = intent.getStringExtra("followers_url")!!

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("${user}'s Followers")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFollowers).apply {
            layoutManager = LinearLayoutManager(this@FollowersActivity)
            recyclerViewAdapter = UsersAdapter(this@FollowersActivity, this@FollowersActivity)
            adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(item: Item) {

    }
}

