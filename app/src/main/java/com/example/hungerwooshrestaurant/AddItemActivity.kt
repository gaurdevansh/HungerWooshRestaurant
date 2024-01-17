package com.example.hungerwooshrestaurant

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hungerwooshrestaurant.databinding.ActivityAddItemBinding
import com.example.hungerwooshrestaurant.model.AllMenu
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDesc: String
    private var foodImage: Uri? = null
    private lateinit var foodIngredient: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.imageSelector.setOnClickListener {
            pickImage.launch("image/*")
        }

        /*binding.imageSelector.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }*/

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.addItemBtm.setOnClickListener {
            checkInputs()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if(uri != null) {
            binding.selectedImage.setImageURI(uri)
        }
    }

    private fun checkInputs() {
        foodName = binding.etFoodName.text.toString().trim()
        foodPrice = binding.etFoodPrice.text.toString().trim()
        foodDesc = binding.etDesc.text.toString().trim()
        //foodImage = binding.imageSelector.
        foodIngredient = binding.etIngredient.text.toString().trim()

        if(!(foodName.isBlank() || foodPrice.isBlank() || foodDesc.isBlank() || foodIngredient.isBlank())) {
            uploadData()
            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadData() {
        val menuRef = database.getReference("menu")
        val newItemKey = menuRef.push().key

        if(foodImage != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(foodImage!!)

            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl ->
                    val newItem = AllMenu(foodName, foodPrice, foodDesc, downloadUrl.toString(), foodIngredient)
                    newItemKey?.let {
                        key ->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this, "Data Upload Failed", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please select an Image", Toast.LENGTH_SHORT).show()
        }
    }
}