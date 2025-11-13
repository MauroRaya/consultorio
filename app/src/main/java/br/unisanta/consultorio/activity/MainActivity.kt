package br.unisanta.consultorio.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.consultorio.R
import br.unisanta.consultorio.dao.ScheduleDao
import br.unisanta.consultorio.model.Schedule
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    private val schedulesDao: ScheduleDao = ScheduleDao()

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fabSignOut = findViewById<FloatingActionButton>(R.id.fab_sign_out)
        val fabSwitchRole = findViewById<FloatingActionButton>(R.id.fab_switch_role)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        val edtName = findViewById<EditText>(R.id.edt_name)

        fabSignOut.setOnClickListener {
            signOut()
            createSignInIntent()
        }

        btnRegister.setOnClickListener {
            val name = edtName.text.toString()

            val schedule = hashMapOf(
                "name" to name
            )

            db
                .collection("schedules")
                .add(schedule)
        }

        fabSwitchRole.setOnClickListener {
            val intent = Intent(this, SchedulesActivity::class.java)
            startActivity(intent)
        }

        createSignInIntent()
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse

        if (result.resultCode == RESULT_OK) {
            // val user = FirebaseAuth.getInstance().currentUser

            Toast.makeText(
                this,
                "Login bem sucedido",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if (response == null) {
                Toast.makeText(
                    this,
                    "Login cancelado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Erro ao realizar login",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
    }
}