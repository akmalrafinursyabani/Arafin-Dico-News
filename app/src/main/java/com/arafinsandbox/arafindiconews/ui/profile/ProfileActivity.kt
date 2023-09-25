package com.arafinsandbox.arafindiconews.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arafinsandbox.arafindiconews.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "My Profile"

        binding.profileLayout.setOnClickListener {
            Toast.makeText(this@ProfileActivity, "Easter Egg 👀", Toast.LENGTH_SHORT).show()
        }
    }
}