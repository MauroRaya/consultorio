package br.unisanta.consultorio.dao

import br.unisanta.consultorio.interfaces.ScheduleInterface
import br.unisanta.consultorio.model.Schedule

class ScheduleDao : ScheduleInterface {
    companion object {
        val schedules: MutableList<Schedule> = mutableListOf()
    }

    override fun getAll(): MutableList<Schedule> {
        return schedules
    }

    override fun insert(schedule: Schedule) {
        schedules.add(schedule)
    }
}