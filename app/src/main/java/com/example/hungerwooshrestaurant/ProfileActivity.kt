package com.example.hungerwooshrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hungerwooshrestaurant.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private var isEditEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableEditing()
        binding.editBtn.setOnClickListener {
            if(!isEditEnable) {
                enableEditing()
                isEditEnable = true
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun disableEditing() {
        binding.etName.isEnabled = false
        binding.etAddress.isEnabled = false
        binding.etEmail.isEnabled = false
        binding.etPhone.isEnabled = false
        binding.etPassword.isEnabled = false
    }

    private fun enableEditing() {
        binding.etName.isEnabled = true
        binding.etAddress.isEnabled = true
        binding.etEmail.isEnabled = true
        binding.etPhone.isEnabled = true
        binding.etPassword.isEnabled = true
        binding.etName.requestFocus()
    }
}