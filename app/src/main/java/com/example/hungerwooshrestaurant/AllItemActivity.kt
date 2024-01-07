package com.example.hungerwooshrestaurant

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungerwooshrestaurant.adapter.AllItemAdapter
import com.example.hungerwooshrestaurant.databinding.ActivityAllItemBinding
import com.example.hungerwooshrestaurant.model.MenuItem

class AllItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllItemBinding
    private lateinit var foodItems : ArrayList<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        setUpFoodItems()
    }

    private fun setUpFoodItems() {
        foodItems = ArrayList()
        foodItems.add(MenuItem("Burger", "$5", R.drawable.burger))
        foodItems.add(MenuItem("Sandwich", "$7", R.drawable.sanwich))
        foodItems.add(MenuItem("Momo", "$8", R.drawable.momo))
        foodItems.add(MenuItem("Fries", "$10", R.drawable.fries))
        foodItems.add(MenuItem("Idli", "$4", R.drawable.idli))
        foodItems.add(MenuItem("Veg Biryani", "$15", R.drawable.vegbiryani))
        val adapter = AllItemAdapter(foodItems)
        binding.allItemsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.allItemsRecyclerview.adapter = adapter
    }
}