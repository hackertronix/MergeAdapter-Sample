package com.hackertronix.mergeadaptersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hackertronix.mergeadaptersample.model.WhatsNew
import kotlinx.android.synthetic.main.item_whats_new.view.*

class WhatsNewAdapter(private val listener: WhatsNewListener) :
    RecyclerView.Adapter<WhatsNewAdapter.WhatsNewViewHolder>() {

    var whatsNew: WhatsNew? = null

    private fun hasWhatsNewData(field: WhatsNew?): Boolean {
        return !field?.description.isNullOrEmpty()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatsNewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_whats_new, parent, false)
        return WhatsNewViewHolder(view)
    }

    override fun getItemCount() = if (hasWhatsNewData(whatsNew)) 1 else 0

    override fun getItemViewType(position: Int): Int = R.layout.item_whats_new

    override fun onBindViewHolder(holder: WhatsNewViewHolder, position: Int) {
        holder.bind(whatsNew, listener)
    }


    inner class WhatsNewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            whatsNew: WhatsNew?,
            listener: WhatsNewListener
        ) {
            itemView.whatsNew.text = whatsNew?.description
            itemView.dismiss.setOnClickListener {
                listener.onDismiss()
            }
        }
    }

    interface WhatsNewListener {
        fun onDismiss()
    }
}