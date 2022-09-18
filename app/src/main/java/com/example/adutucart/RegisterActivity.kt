package com.example.adutucart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adutucart.databinding.ActivityRegisterBinding //for binding Register Activity and activity_register
import com.google.firebase.auth.FirebaseAuth // for authentication for Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding //for binding Register Activity and activity_register
    private lateinit var firebaseAuth: FirebaseAuth // for authentication for Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful) {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Empty fields are not allowed !!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}