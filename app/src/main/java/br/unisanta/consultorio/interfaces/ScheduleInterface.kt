package br.unisanta.consultorio.interfaces

import br.unisanta.consultorio.model.Schedule

interface ScheduleInterface {
    fun getAll(): MutableList<Schedule>
    fun insert(schedule: Schedule)
}