package com.example.testingcalldetectionlibrary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mywatchoutlibrary.Watchout
import com.example.testingcalldetectionlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var nextButton: Button

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_btn)

        nextButton.setOnClickListener {
            var intent: Intent
            intent = Intent(this@MainActivity, Watchout::class.java)
            startActivity(intent)
        }

    }
}