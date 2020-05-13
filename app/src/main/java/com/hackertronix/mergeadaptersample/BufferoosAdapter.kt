package com.hackertronix.mergeadaptersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hackertronix.mergeadaptersample.model.Bufferoo
import kotlinx.android.synthetic.main.item_bufferoo.view.*
import kotlinx.android.synthetic.main.item_whats_new.view.*

class BufferoosAdapter() :
    RecyclerView.Adapter<BufferoosAdapter.BufferoosViewHolder>() {

    var bufferoos: List<Bufferoo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BufferoosViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bufferoo, parent, false)
        return BufferoosViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_bufferoo


    override fun getItemCount(): Int {
        return bufferoos.size
    }

    override fun onBindViewHolder(holder: BufferoosViewHolder, position: Int) {
        holder.bind(bufferoos[position])
    }

    inner class BufferoosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bufferoo: Bufferoo) {
            itemView.bufferooName.text = bufferoo.name
            itemView.bufferooRole.text = bufferoo.role
            Glide.with(itemView.context)
                .load(bufferoo.image)
                .apply(RequestOptions().circleCrop())
                .into(itemView.image)
        }

    }
}