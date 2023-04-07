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
//    lateinit var userDetails: UserDetails

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        nextButton = findViewById(R.id.next_btn)

//        userDetails = UserDetails(UserUser("Shweta@mailinator.com","Shweta", "kumari", "85856895885","Female"),
//            "ola", "1221117105526941", "EmergencyMessage", "p", UserVehicle("1FADP3J27EL153248",
//                "Ford", "RJ16CH7898", "2014","Focus","Ambulance"),
//            "26.9124", "Audio", "75.7873")

        val userDetails : String =  "{\n" +
                "  \"apiAuthrizationKey\": \"1221117105526941\",\n" +
                "  \"longitude\": 26.9124,\n" +
                "  \"latitude\": 75.7873,\n" +
                "  \"tripBy\": \"ola\",\n" +
                "  \"userType\": \"p\",\n" +
                "  \"caseIncomingType\":\"Audio\",\n" +
                "  \"user\": {\n" +
                "    \"firstName\": \"vicky\",\n" +
                "    \"lastName\": \"kumar\",\n" +
                "    \"email\": \"vicky3i@gmail.com\",\n" +
                "    \"phone\": \"85856895885\",\n" +
                "    \"gender\": \"Male\"\n" +
                "    \n" +
                "  },\n" +
                "  \"vehicle\": {\n" +
                "    \"vehYear\": \"2014\",\n" +
                "    \"vehMake\": \"Ford\",\n" +
                "    \"vinNum\": \"1FADP3J27EL153248\",\n" +
                "    \"vehicle_model\": \"Focus\",\n" +
                "    \"vehicle_number\": \"RJ16CH7898\",\n" +
                "    \"vehicleType\": \"Ambulance\"\n" +
                "  }\n" +
                "}"
        nextButton.setOnClickListener {
            var intent: Intent = Intent()
            intent = Intent(this@MainActivity, Watchout::class.java)
            intent.putExtra("user_details", userDetails)
            Log.d("Your_Data", userDetails)
            startActivity(intent)
        }

    }

}