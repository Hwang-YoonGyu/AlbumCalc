package com.example.albumcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.albumcalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val bu:Int = Integer.parseInt(binding.editBu.text.toString())
            val meon:Int = Integer.parseInt(binding.editMeon.text.toString())

            binding.FinalPrice.text = (bu * meon).toString()
        }

    }






}