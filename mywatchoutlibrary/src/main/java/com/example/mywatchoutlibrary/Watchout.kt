package com.example.mywatchoutlibrary

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Watchout() : AppCompatActivity(), View.OnClickListener {

    lateinit var medicalBtn1: Button
    lateinit var accidentBtn1: Button
    lateinit var securityBtn1: Button
    lateinit var medicalCall1: Button
    lateinit var medicalSos1: Button
    lateinit var accidentCall1: Button
    lateinit var accidentSos1: Button
    lateinit var securityCall1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchout)

        medicalBtn1 = findViewById(R.id.medical_btn)
        accidentBtn1 = findViewById(R.id.accident_btn)
        securityBtn1 = findViewById(R.id.secutity_btn)
        medicalCall1 = findViewById(R.id.medical_call)
        medicalSos1 = findViewById(R.id.medical_sos)
        accidentCall1 = findViewById(R.id.accident_call)
        accidentSos1 = findViewById(R.id.accident_sos)
        securityCall1 = findViewById(R.id.security_call)

        medicalBtn1.setOnClickListener(this)
        accidentBtn1.setOnClickListener(this)
        securityBtn1.setOnClickListener(this)
        medicalCall1.setOnClickListener(this)
        medicalSos1.setOnClickListener(this)
        accidentCall1.setOnClickListener(this)
        accidentSos1.setOnClickListener(this)
        securityCall1.setOnClickListener(this)
        supportActionBar?.hide()
        CallDetection.emergencyUiDetails(
            this,
            "1221117105526941",
            medicalBtn1,
            accidentBtn1,
            securityBtn1
        )

    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.medical_btn) {

            CallDetection.medicalVisibility(medicalCall1, medicalSos1)

        }
        if (v?.id == R.id.medical_call) {
            CallDetection.medicalAccidentCallApi(
                this,
                "Audio",
                CallDetection.getStringValue(CallDetection.MEDICAL_GROUP_ID, this)!!,
                "1221117105526941",
                "ola",
                "75.7873",
                "p",
                "Shweta",
                "kumari",
                "Female",
                "85856895885",
                "Shweta@mailinator.com",
                "26.9124",
                "EmergencyMessage",
                "Ford",
                "RJ16CH7898",
                "Focus",
                "2014",
                "1FADP3J27EL153248",
                "Ambulance"
            )
            CallDetection.setSBoolenVale("accClick", false, this)
            CallDetection.setSBoolenVale("medClick", true, this)
        }
        if (v?.id == R.id.medical_sos) {
            CallDetection.sosApi(
                this,
                "SOS",
                CallDetection.getStringValue(CallDetection.MEDICAL_GROUP_ID, this)!!,
                "1221117105526941",
                "ola",
                "75.7873",
                "p",
                "Shweta",
                "kumari",
                "Female",
                "85856895885",
                "Shweta@mailinator.com",
                "26.9124",
                "EmergencyMessage",
                "Ford",
                "RJ16CH7898",
                "Focus",
                "2014",
                "1FADP3J27EL153248",
                "Ambulance"
            )
        }
        if (v?.id == R.id.accident_btn) {
            CallDetection.accidentVisibility(accidentCall1, accidentSos1)
        }
        if (v?.id == R.id.accident_call) {
            CallDetection.medicalAccidentCallApi(
                this,
                "Audio",
                CallDetection.getStringValue(CallDetection.ACCIDENT_GROUP_ID, this)!!,
                "1221117105526941",
                "ola",
                "75.7873",
                "p",
                "Shweta",
                "kumari",
                "Female",
                "85856895885",
                "Shweta@mailinator.com",
                "26.9124",
                "EmergencyMessage",
                "Ford",
                "RJ16CH7898",
                "Focus",
                "2014",
                "1FADP3J27EL153248",
                "Ambulance"
            )
            CallDetection.setSBoolenVale(CallDetection.MEDICAL_CLICK, false, this)
            CallDetection.setSBoolenVale(CallDetection.ACCIDENT_CLICK, true, this)
        }
        if (v?.id == R.id.accident_sos) {
            CallDetection.sosApi(
                this,
                "SOS",
                CallDetection.getStringValue(CallDetection.ACCIDENT_GROUP_ID, this)!!,
                "1221117105526941",
                "ola",
                "75.7873",
                "p",
                "Shweta",
                "kumari",
                "Female",
                "85856895885",
                "Shweta@mailinator.com",
                "26.9124",
                "EmergencyMessage",
                "Ford",
                "RJ16CH7898",
                "Focus",
                "2014",
                "1FADP3J27EL153248",
                "Ambulance"
            )
        }
        if (v?.id == R.id.secutity_btn) {
            CallDetection.securityVisibility(securityCall1)
        }
        if (v?.id == R.id.security_call) {
            CallDetection.securityCallApi(
                this,
                "Audio",
                CallDetection.getStringValue(CallDetection.SECURITY_GROUP_ID, this)!!,
                "1221117105526941",
                "ola",
                "75.7873",
                "p",
                "Shweta",
                "kumari",
                "Female",
                "85856895885",
                "Shweta@mailinator.com",
                "26.9124",
                "EmergencyMessage",
                "Ford",
                "RJ16CH7898",
                "Focus",
                "2014",
                "1FADP3J27EL153248",
                "Ambulance"

            )
        }


    }
}