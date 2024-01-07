package com.example.hungerwooshrestaurant.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungerwooshrestaurant.databinding.DeliveryItemBinding
import com.example.hungerwooshrestaurant.databinding.FoodItemBinding
import com.example.hungerwooshrestaurant.model.DeliveryItem
import com.example.hungerwooshrestaurant.model.MenuItem

class DeliveryAdapter(private val deliveryItems: MutableList<DeliveryItem>) : RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = DeliveryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeliveryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return deliveryItems.size
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class DeliveryViewHolder(private val binding: DeliveryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = deliveryItems[position].customerName
                moneyStatus.text = deliveryItems[position].moneyStaus
                val colorMap = mapOf(
                    "Received" to Color.GREEN,
                    "Not Received" to Color.RED,
                    "Pending" to Color.GRAY
                )
                moneyStatus.setTextColor(colorMap[deliveryItems[position].moneyStaus]?:Color.BLACK)
                statusIndicator.backgroundTintList = ColorStateList.valueOf(colorMap[deliveryItems[position].moneyStaus]?:Color.BLACK)
            }
        }
    }
}