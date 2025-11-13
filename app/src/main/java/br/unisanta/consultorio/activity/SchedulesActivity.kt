package br.unisanta.consultorio.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.consultorio.R
import br.unisanta.consultorio.adapter.ScheduleAdapter
import br.unisanta.consultorio.dao.ScheduleDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SchedulesActivity : AppCompatActivity() {
    private val schedulesDao = ScheduleDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_schedules)

        val fabFinish = findViewById<FloatingActionButton>(R.id.fab_finish)

        val rvSchedules = findViewById<RecyclerView>(R.id.rv_schedules)
        val schedules = schedulesDao.getAll()

        fabFinish.setOnClickListener {
            finish()
        }

        rvSchedules.layoutManager = LinearLayoutManager(this)
        rvSchedules.adapter = ScheduleAdapter(schedules)
    }
}