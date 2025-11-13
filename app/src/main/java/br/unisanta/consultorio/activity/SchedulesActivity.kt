package br.unisanta.consultorio.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.consultorio.R
import br.unisanta.consultorio.adapter.ScheduleAdapter
import br.unisanta.consultorio.model.Schedule
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class SchedulesActivity : AppCompatActivity() {
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_schedules)

        val rvSchedules = findViewById<RecyclerView>(R.id.rv_schedules)

        val task: Task<QuerySnapshot> = db
            .collection("schedules")
            .get()

        task.addOnSuccessListener { querySnapshot ->
            val schedules: MutableList<Schedule> = querySnapshot.documents
                .mapNotNull { it.toObject<Schedule>() }
                .toMutableList()

            rvSchedules.adapter = ScheduleAdapter(schedules)
        }

        rvSchedules.layoutManager = LinearLayoutManager(this)
    }
}