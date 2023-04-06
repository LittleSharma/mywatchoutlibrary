package com.example.testingcalldetectionlibrary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mywatchoutlibrary.UserDetails
import com.example.mywatchoutlibrary.UserUser
import com.example.mywatchoutlibrary.UserVehicle
import com.example.mywatchoutlibrary.Watchout
import com.example.testingcalldetectionlibrary.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var nextButton: Button
    lateinit var userDetails: UserDetails

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_btn)

        userDetails = UserDetails(UserUser("Shweta@mailinator.com","Shweta", "kumari", "85856895885","Female"),
            "ola", "1221117105526941", "EmergencyMessage", "p", UserVehicle("1FADP3J27EL153248",
                "Ford", "RJ16CH7898", "2014","Focus","Ambulance"),
            "26.9124", "Audio", "75.7873")

        val gson = Gson()
        val json = gson.toJson(userDetails)
        println(json)
        nextButton.setOnClickListener {
            var intent: Intent = Intent()
            intent = Intent(this@MainActivity, Watchout::class.java)
            intent.putExtra("user_details", json)
            Log.d("Your_Data", json)
            startActivity(intent)
        }

    }

}