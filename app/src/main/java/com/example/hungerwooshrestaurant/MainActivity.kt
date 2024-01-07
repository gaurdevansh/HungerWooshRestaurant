package com.example.hungerwooshrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hungerwooshrestaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMenuCard.setOnClickListener(this)
        binding.allItemCard.setOnClickListener(this)
        binding.dispatchCard.setOnClickListener(this)
        binding.profileCard.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            binding.addMenuCard -> startActivity(Intent(this, AddItemActivity::class.java))
            binding.allItemCard -> startActivity(Intent(this, AllItemActivity::class.java))
            binding.dispatchCard -> startActivity(Intent(this, OutForDeliveryActivity::class.java))
            binding.profileCard -> startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}