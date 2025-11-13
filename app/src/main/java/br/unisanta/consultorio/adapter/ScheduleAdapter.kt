package br.unisanta.consultorio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.consultorio.model.Schedule
import br.unisanta.consultorio.viewholder.ScheduleViewHolder
import br.unisanta.consultorio.R

class ScheduleAdapter(
    private val schedules: MutableList<Schedule>
) : RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.schedule_item, parent, false)

        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]

        holder.txvName.text = schedule.name
    }

    override fun getItemCount(): Int {
        return schedules.size
    }
}