package com.example.swipedelete.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipedelete.Model.Item
import com.example.swipedelete.databinding.CustomLayoutBinding

class CustomAdapter(private val itemlist:ArrayList<Item>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(val binding: CustomLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=CustomLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=itemlist[position]

        holder.binding.firstname.text=item.firstname
        holder.binding.lastname.text=item.lastname
        holder.binding.age.text=item.age.toString()
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}