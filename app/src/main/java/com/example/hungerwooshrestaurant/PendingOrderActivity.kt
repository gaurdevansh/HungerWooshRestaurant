package com.example.hungerwooshrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungerwooshrestaurant.adapter.PendingOrderAdapter
import com.example.hungerwooshrestaurant.databinding.ActivityPendingOrderBinding
import com.example.hungerwooshrestaurant.model.MenuItem
import com.example.hungerwooshrestaurant.model.PendingOrderItem

class PendingOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPendingOrderBinding
    private lateinit var pendingOrderItems : ArrayList<PendingOrderItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPendingOrder()
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun setUpPendingOrder() {
        pendingOrderItems = ArrayList()
        pendingOrderItems.add(PendingOrderItem("John Clark", 5, R.drawable.idli))
        pendingOrderItems.add(PendingOrderItem("Virat Sharma", 2, R.drawable.burger))
        pendingOrderItems.add(PendingOrderItem("Rohit Kumar", 1, R.drawable.vegbiryani))
        pendingOrderItems.add(PendingOrderItem("Andrew Maxwell", 3, R.drawable.momo))
        pendingOrderItems.add(PendingOrderItem("Rahul Kohli", 4, R.drawable.sanwich))
        val adapter = PendingOrderAdapter(pendingOrderItems, this)
        binding.pendingOrderRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.pendingOrderRecyclerview.adapter = adapter
    }
}