package com.example.hungerwooshrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungerwooshrestaurant.adapter.DeliveryAdapter
import com.example.hungerwooshrestaurant.databinding.ActivityOutForDeliveryBinding
import com.example.hungerwooshrestaurant.model.DeliveryItem

class OutForDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutForDeliveryBinding
    private lateinit var deliveryList: ArrayList<DeliveryItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutForDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpDeliveryRecyclerview()
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun setUpDeliveryRecyclerview() {
        deliveryList = ArrayList()
        deliveryList.add(DeliveryItem("John Clark", "Received"))
        deliveryList.add(DeliveryItem("Andrew Maxwell", "Not Received"))
        deliveryList.add(DeliveryItem("Virat Sharma", "Received"))
        deliveryList.add(DeliveryItem("Rohit Kumar", "Pending"))
        val adapter = DeliveryAdapter(deliveryList)
        binding.deliveryRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.deliveryRecyclerview.adapter = adapter
    }
}