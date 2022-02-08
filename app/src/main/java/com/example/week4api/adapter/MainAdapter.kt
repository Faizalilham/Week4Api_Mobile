package com.example.week4api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week4api.Model.Barang
import com.example.week4api.databinding.ListItemBinding

class MainAdapter(var barang: List<Barang>, val listener: AdapterClick) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface AdapterClick{
        fun onRead(barang: Barang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = barang[position].nama
            tvTitle.setOnClickListener {
                listener.onRead(barang[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return barang.size
    }

}