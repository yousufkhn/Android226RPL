package com.example.a226complete.unit1.customgridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.a226complete.R

class GridAdapter(
    private val context: Context,
    private val titles: List<String>,
    private val images: List<Int>
) : BaseAdapter() {

    override fun getCount() = titles.size
    override fun getItem(position: Int) = titles[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false)
        view.findViewById<ImageView>(R.id.icon).setImageResource(images[position])
        view.findViewById<TextView>(R.id.title).text = titles[position]
        return view
    }
}