package com.example.hungerwooshrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hungerwooshrestaurant.databinding.ActivityCreateUserBinding

class CreateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}