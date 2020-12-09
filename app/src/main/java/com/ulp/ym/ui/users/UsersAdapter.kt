package com.ulp.ym.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulp.ym.R
import com.ulp.ym.data.Item
import com.ulp.ym.interfaces.ItemClickListener
import com.ulp.ym.util.imageUtil.ImageLoader

class UsersAdapter(
    private val listener: ItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var dataSet: List<Item> = ArrayList()

    fun setListData(data: ArrayList<Item>) {
        this.dataSet = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position].login
        ImageLoader.with(context).load(holder.imageView, dataSet[position].avatar_url)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val textView: TextView
        val imageView: ImageView

        init {
            view.setOnClickListener(this)
            textView = view.findViewById(R.id.itemTitle)
            imageView = view.findViewById(R.id.itemImage)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onItemClick(dataSet.get(position))
        }
    }
}