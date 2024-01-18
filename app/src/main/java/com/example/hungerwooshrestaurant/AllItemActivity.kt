package com.example.hungerwooshrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hungerwooshrestaurant.adapter.MenuItemAdapter
import com.example.hungerwooshrestaurant.databinding.ActivityAllItemBinding
import com.example.hungerwooshrestaurant.model.AllMenu
import com.example.hungerwooshrestaurant.model.MenuItem
import com.google.firebase.Firebase
import com.google.firebase.database.*

private const val TAG = "AllItemActivity"
class AllItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllItemBinding
    private lateinit var menuItems : ArrayList<AllMenu>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = Firebase.database.reference

        binding.backBtn.setOnClickListener {
            finish()
        }

        retrieveMenuItems()
    }

    private fun retrieveMenuItems() {
        menuItems = ArrayList()

        val foodRef = databaseReference.child("menu")
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItems.clear()
                for(foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(AllMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d(TAG, "onCancelled: ", error.toException())
            }
        })
    }

    private fun setAdapter() {
        val adapter = MenuItemAdapter(menuItems, this, databaseReference)
        binding.allItemsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.allItemsRecyclerview.adapter = adapter
    }
}