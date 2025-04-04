package com.example.gradeup

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gradeup.data.model.SubjectModel
import com.example.gradeup.data.remote.RetrofitClient
import com.example.gradeup.data.remote.SubjectService
import com.example.gradeup.data.repository.SubjectRepository
import com.example.gradeup.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val subRepo = SubjectRepository()
        subRepo.getSubjects()



//        // Recebimento dos dados de maneira assincrona
//        val service = RetrofitClient.createService(SubjectService::class.java)
//        val call: Call<List<SubjectModel>> = service.list()
//        call.enqueue(object : Callback<List<SubjectModel>>{
//            override fun onResponse(call: Call<List<SubjectModel>>, response: Response<List<SubjectModel>>) {
//                val list = response.body()
//            }
//
//            override fun onFailure(call: Call<List<SubjectModel>>, t: Throwable) {
//                val s = ""
//            }
//
//        })

    }
}