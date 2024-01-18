package com.example.hungerwooshrestaurant.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungerwooshrestaurant.databinding.FoodItemBinding
import com.example.hungerwooshrestaurant.model.AllMenu
import com.example.hungerwooshrestaurant.model.MenuItem
import com.google.firebase.database.DatabaseReference

class MenuItemAdapter(private val menuItems: MutableList<AllMenu>,
private val context: Context,
private val databaseReference: DatabaseReference) : RecyclerView.Adapter<MenuItemAdapter.AllItemViewHolder>() {

    private val itemQuantities = IntArray(menuItems.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemViewHolder {
        val binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class AllItemViewHolder(private val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                foodName.text = menuItems[position].foodName
                foodPrice.text = menuItems[position].foodPrice
                Glide.with(context).load(Uri.parse(menuItems[position].foodImage)).into(foodPoster)
                foodQuantity.text = quantity.toString()
                subtractBtn.setOnClickListener {
                    if(itemQuantities[position] > 1) {
                        itemQuantities[position]--
                        foodQuantity.text = itemQuantities[position].toString()
                    }
                }
                addBtn.setOnClickListener {
                    if(itemQuantities[position] < 10) {
                        itemQuantities[position]++
                        foodQuantity.text = itemQuantities[position].toString()
                    }
                }
                deleteBtn.setOnClickListener {
                    if(adapterPosition != RecyclerView.NO_POSITION) {
                        menuItems.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, menuItems.size)
                    }
                }
            }
        }
    }
}