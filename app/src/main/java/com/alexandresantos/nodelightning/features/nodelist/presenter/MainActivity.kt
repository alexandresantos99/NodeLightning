package com.alexandresantos.nodelightning.features.nodelist.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexandresantos.nodelightning.R
import com.alexandresantos.nodelightning.databinding.ActivityMainBinding
import com.alexandresantos.nodelightning.features.nodelist.presenter.viewmodel.NodeListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<NodeListViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}