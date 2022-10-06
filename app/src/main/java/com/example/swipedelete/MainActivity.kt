package com.example.swipedelete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipedelete.Adapter.CustomAdapter
import com.example.swipedelete.Model.Item
import com.example.swipedelete.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item1=Item("Ted","Omino",23)
        val item2=Item("Israel","Omino",20)
        val item3=Item("Brayden","Omino",9)
        val item4=Item("Valarie","Omino",33)
        val item5=Item("Egbert","Omino",31)
        val item6=Item("Young","Omino",28)

        val items= arrayListOf<Item>()
        items.add(item1)
        items.add(item2)
        items.add(item3)
        items.add(item4)
        items.add(item5)
        items.add(item6)

        adapter= CustomAdapter(items)
        binding.recyclerItems.setHasFixedSize(true)
        binding.recyclerItems.layoutManager=LinearLayoutManager(this)
        binding.recyclerItems.adapter=adapter

        val delete=object :SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val deletedItem=items[position]
                items.removeAt(position)
                binding.recyclerItems.adapter?.notifyItemRemoved(position)

                Snackbar.make(binding.recyclerItems,"Deleted ${deletedItem.firstname} ${deletedItem.lastname}.",Snackbar.LENGTH_LONG).
                        setAction("Undo", View.OnClickListener {
                            items.add(position,deletedItem)
                            binding.recyclerItems.adapter?.notifyItemInserted(position)
                        }).show()
            }

        }
        val itemTouchHelper=ItemTouchHelper(delete)

        itemTouchHelper.attachToRecyclerView(binding.recyclerItems)


    }
}