package com.alexandresantos.nodelightning.features.nodelist.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexandresantos.nodelightning.R
import com.alexandresantos.nodelightning.databinding.ResNodeItemBinding
import com.alexandresantos.nodelightning.features.commons.satsToBtc
import com.alexandresantos.nodelightning.features.commons.timestampToBrFormat
import com.alexandresantos.nodelightning.features.nodelist.data.models.NodeListResponse

class MainAdapter(private val context: Context) :
    ListAdapter<Pair<String, NodeListResponse>, MainAdapter.MainViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResNodeItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currencyPair = getItem(position)
        holder.bind(currencyPair)
    }


    class MainViewHolder(private val context: Context, private val binding: ResNodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pair: Pair<String, NodeListResponse>) {
            binding.publicKey.text = pair.second.publicKey
            binding.alias.text = pair.second.alias
            binding.channels.text =
                context.getString(R.string.node_list_channels, pair.second.channels.toString())
            binding.capacity.text =
                context.getString(
                    R.string.node_list_btc_amount_format,
                    pair.second.capacity.satsToBtc()
                )
            binding.firstSeen.text =
                context.getString(
                    R.string.node_list_public_in,
                    pair.second.firstSeen.timestampToBrFormat()
                )
            binding.updateAt.text =
                context.getString(
                    R.string.node_list_last_update,
                    pair.second.updatedAt.timestampToBrFormat()
                )

            bindCity(pair)
        }

        private fun bindCity(pair: Pair<String, NodeListResponse>) {
            if (pair.second.city?.ptBr != null) {
                binding.layoutCity.visibility = View.VISIBLE
                binding.country.text = pair.second.country?.ptBr
                binding.city.text = pair.second.city?.ptBr
            } else binding.layoutCity.visibility = View.GONE
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<Pair<String, NodeListResponse>>() {
        override fun areItemsTheSame(
            oldItem: Pair<String, NodeListResponse>,
            newItem: Pair<String, NodeListResponse>
        ): Boolean {
            return oldItem.first == newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, NodeListResponse>,
            newItem: Pair<String, NodeListResponse>
        ): Boolean {
            return oldItem.second.publicKey == newItem.second.publicKey
        }
    }
}