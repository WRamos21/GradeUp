package com.example.gradeup

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gradeup.data.local.SubjectDatabase
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.RetrofitClient
import com.example.gradeup.data.remote.SubjectService
import com.example.gradeup.data.repository.SubjectRepository
import com.example.gradeup.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupNavigation()
    }

    private fun setupNavigation(){
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

//    private fun testarBancoRoom() {
//        // Apenas pegar a instância NÃO cria o banco físico
//        val database = SubjectDatabase.getDatabase(this)
//        Log.d("ROOM_DB", "Instância do banco obtida")
//
//        // O banco só é REALMENTE criado quando fazemos a primeira consulta
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                Log.d("ROOM_DB", "Fazendo primeira consulta para forçar criação...")
//
//                // ESTA linha vai disparar o onCreate do Callback
//                val subjects = database.subjectDAO().getAllSubject()
//
//                withContext(Dispatchers.Main) {
//                    Log.d("ROOM_DB", "Consulta executada! Total: ${subjects.size}")
//                    subjects.forEach { subject ->
//                        Log.d("ROOM_DB", "Disciplina: ${subject.disciplina}")
//                    }
//                }
//
//            } catch (e: Exception) {
//                Log.e("ROOM_DB", "Erro ao consultar banco: ${e.message}")
//                e.printStackTrace()
//            }
//        }
//    }
}