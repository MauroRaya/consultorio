package br.unisanta.consultorio.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.consultorio.R

class ScheduleViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView) {
    val txvName = itemView.findViewById<TextView>(R.id.txv_name)
}