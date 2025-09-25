package com.example.a226complete.ca1

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
    private val contacts: List<Contact>): BaseAdapter() {
    override fun getCount() = contacts.size
    override fun getItem(position: Int) = contacts[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)

        val contact = getItem(position)

        val nameTextView = view.findViewById<TextView>(R.id.name)
        val numberTextView = view.findViewById<TextView>(R.id.number)

        nameTextView.text = contact.name
        numberTextView.text = contact.number.toString()

        return view
    }
}