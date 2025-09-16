package com.example.a226complete.unit1.customlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.a226complete.R

class CustomListAdapter(private val context: Context, private val data: List<User>) : BaseAdapter() {
    override fun getCount() = data.size
    override fun getItem(position: Int) = data[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        val user = data[position]

        view.findViewById<TextView>(R.id.name).text = user.name
        view.findViewById<TextView>(R.id.email).text = user.email

        return view
    }
}