package com.alexandresantos.nodelightning.features.nodelist.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexandresantos.nodelightning.R
import com.alexandresantos.nodelightning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}