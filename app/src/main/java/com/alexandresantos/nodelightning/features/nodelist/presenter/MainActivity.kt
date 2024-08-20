package com.alexandresantos.nodelightning.features.nodelist.presenter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexandresantos.nodelightning.databinding.ActivityMainBinding
import com.alexandresantos.nodelightning.features.nodelist.presenter.state.NodeListState
import com.alexandresantos.nodelightning.features.nodelist.presenter.viewmodel.NodeListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<NodeListViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getAllNodes()
        subscribeApi()
    }

    private fun subscribeApi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.nodesLiveData.observe(this@MainActivity) { state ->
                    when (state) {
                        is NodeListState.Success -> Log.i("Test Success", "${state.data}")
                        is NodeListState.Error -> Log.i("Test Error", "Error")
                        is NodeListState.Loading -> Log.i("Test Loading", "Loading")

                    }
                }
            }
        }
    }
}