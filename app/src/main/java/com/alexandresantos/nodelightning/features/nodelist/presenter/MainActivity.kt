package com.alexandresantos.nodelightning.features.nodelist.presenter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexandresantos.nodelightning.databinding.ActivityMainBinding
import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse
import com.alexandresantos.nodelightning.features.nodelist.presenter.adapter.MainAdapter
import com.alexandresantos.nodelightning.features.nodelist.presenter.state.NodeListState
import com.alexandresantos.nodelightning.features.nodelist.presenter.viewmodel.NodeListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<NodeListViewModel>()

    private val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvNodelistList.adapter = adapter
        viewModel.getAllNodes()
        subscribeApi()
        reloadButtonListener()
    }

    private fun reloadButtonListener() {
        binding.fabNodelistUpdateList.setOnClickListener {
            viewModel.getAllNodes()
        }
    }

    private fun subscribeApi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.nodesLiveData.observe(this@MainActivity) { state ->
                    when (state) {
                        is NodeListState.Success -> bindUI(state.data)
                        is NodeListState.Error -> bindError()
                        is NodeListState.Loading -> bindLoading()
                    }
                }
            }
        }
    }

    private fun bindError() {
        binding.pbNodelistLoading.visibility = View.GONE
        binding.rvNodelistList.visibility = View.GONE
        binding.llNodelistError.visibility = View.VISIBLE
    }

    private fun bindLoading() {
        binding.pbNodelistLoading.visibility = View.VISIBLE
        binding.rvNodelistList.visibility = View.GONE
        binding.llNodelistError.visibility = View.GONE
    }

    private fun bindUI(data: List<NodeListResponse>) {
        Log.i("Debugando", "Caiu aqui")
        binding.pbNodelistLoading.visibility = View.GONE
        binding.rvNodelistList.visibility = View.VISIBLE

        val transformedData = data.map { response ->
            Pair(response.publicKey, response)
        }
        adapter.submitList(transformedData)
    }
}