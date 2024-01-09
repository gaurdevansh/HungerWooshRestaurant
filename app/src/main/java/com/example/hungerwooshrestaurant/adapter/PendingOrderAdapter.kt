package com.example.hungerwooshrestaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hungerwooshrestaurant.databinding.PendingOrderItemBinding
import com.example.hungerwooshrestaurant.model.PendingOrderItem

class PendingOrderAdapter(private val pendingOrderItems: MutableList<PendingOrderItem>, private val context: Context) : RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val binding = PendingOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PendingOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pendingOrderItems.size
    }

    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class PendingOrderViewHolder(private val binding: PendingOrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var isAccepted = false
        fun bind(position: Int) {
            binding.apply {
                customerName.text = pendingOrderItems[position].name
                foodQuantity.text = pendingOrderItems[position].quantity.toString()
                foodPoster.setImageResource(pendingOrderItems[position].image)
                acceptBtn.apply {
                    if(!isAccepted) {
                        text = "Accept"
                    } else {
                        text = "Dispatched"
                    }
                    setOnClickListener {
                        if(!isAccepted) {
                            text = "Dispatched"
                            isAccepted = true
                            showToast("Order is Accepted")
                        } else {
                            pendingOrderItems.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order is Dispatched")
                        }
                    }
                }
            }
        }
        private fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}