package com.example.hungerwooshrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hungerwooshrestaurant.databinding.ActivityLoginBinding
import com.example.hungerwooshrestaurant.model.RestaurantUser
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        database = Firebase.database.reference

        binding.btnLogin.setOnClickListener {
            checkInputs()

        }
        binding.tvDontHaveAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUi(currentUser)
        }
    }

    private fun checkInputs() {
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
        } else {
            createUserAccount()
        }
    }

    private fun createUserAccount() {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show()
                updateUi(user)
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        saveUserData()
                        Toast.makeText(this, "User Created and Login Successfull", Toast.LENGTH_SHORT).show()
                        updateUi(user)
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Authentication Failed: ", task.exception)
                    }
                }
            }
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun saveUserData() {
        val user = RestaurantUser(email = email, password = password)
        val userId: String = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d(TAG, "User Data successfully written")
                } else {
                    Log.d(TAG, "Data writing failed :", task.exception)
                }
            }
    }
}