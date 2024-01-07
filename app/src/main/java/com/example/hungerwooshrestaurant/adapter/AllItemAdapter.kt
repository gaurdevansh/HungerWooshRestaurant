package com.example.hungerwooshrestaurant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungerwooshrestaurant.databinding.FoodItemBinding
import com.example.hungerwooshrestaurant.model.MenuItem

class AllItemAdapter(private val menuItems: MutableList<MenuItem>) : RecyclerView.Adapter<AllItemAdapter.AllItemViewHolder>() {

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
                foodName.text = menuItems[position].name
                foodPrice.text = menuItems[position].price
                foodPoster.setImageResource(menuItems[position].image)
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