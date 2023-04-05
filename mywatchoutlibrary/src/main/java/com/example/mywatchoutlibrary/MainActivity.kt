package com.example.mywatchoutlibrary

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mywatchoutlibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.medicalBtn.setOnClickListener(this)
        binding.accidentBtn.setOnClickListener(this)
        binding.secutityBtn.setOnClickListener(this)
        binding.medicalCall.setOnClickListener(this)
        binding.medicalSos.setOnClickListener(this)
        binding.accidentCall.setOnClickListener(this)
        binding.accidentSos.setOnClickListener(this)
        binding.securityCall.setOnClickListener(this)
        supportActionBar?.hide()
        CallDetection.emergencyUiDetails(
            this,
            "1221117105526941",
            binding.medicalBtn,
            binding.accidentBtn,
            binding.secutityBtn
        )
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.medical_btn) {

            CallDetection.medicalVisibility(binding.medicalCall, binding.medicalSos)

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
            CallDetection.accidentVisibility(binding.accidentCall, binding.accidentSos)

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
            CallDetection.securityVisibility(binding.securityCall)
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