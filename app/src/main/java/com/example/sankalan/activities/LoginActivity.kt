package com.example.sankalan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sankalan.databinding.LoginActivityBinding
import com.example.sankalan.interfaces.LoginFragmentHandlers

class LoginActivity : AppCompatActivity(),LoginFragmentHandlers {


    lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun changeActivity() {
        //startActivity(Intent(this))
    }

    override fun changeFragment() {
        TODO("Not yet implemented")

    }
}