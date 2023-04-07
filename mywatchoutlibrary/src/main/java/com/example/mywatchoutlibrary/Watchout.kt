package com.example.mywatchoutlibrary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class Watchout : AppCompatActivity(), View.OnClickListener {

    lateinit var medicalBtn1: Button
    lateinit var accidentBtn1: Button
    lateinit var securityBtn1: Button
    lateinit var medicalCall1: Button
    lateinit var medicalSos1: Button
    lateinit var accidentCall1: Button
    lateinit var accidentSos1: Button
    lateinit var securityCall1: Button
    lateinit var userRecieveData: UserDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchout)

        val json = intent.getStringExtra("user_details")
        Log.d("Your_Recieve_Data", json!!)
        val gson = Gson()
        userRecieveData = gson.fromJson(json, UserDetails::class.java)
        Log.d("Your_Receive_Data", userRecieveData.toString())

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

        CallDetection.setupPermissions(this)
        CallDetection.emergencyUiDetails(
            this,
            userRecieveData.userAuthkey.toString(),
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
                userRecieveData.userAuthkey.toString(),
                userRecieveData.userTripBy.toString(),
                userRecieveData.userLatitude.toString(),
                userRecieveData.userUserType.toString(),
                userRecieveData.userUser?.userFirstName.toString(),
                userRecieveData.userUser?.userLastName.toString(),
                userRecieveData.userUser?.userGender.toString(),
                userRecieveData.userUser?.userPhone.toString(),
                userRecieveData.userUser?.userEmail.toString(),
                userRecieveData.userLongitude.toString(),
                userRecieveData.userCaseType.toString(),
                userRecieveData.userVehicle?.userVehMake.toString(),
                userRecieveData.userVehicle?.userVehicleNumber.toString(),
                userRecieveData.userVehicle?.userVehicleModel.toString(),
                userRecieveData.userVehicle?.userVehYear.toString(),
                userRecieveData.userVehicle?.userVinNum.toString(),
                userRecieveData.userVehicle?.userVehicleType.toString()
            )
            CallDetection.setSBoolenVale("accClick", false, this)
        }
        if (v?.id == R.id.medical_sos) {

            CallDetection.sosApi(
                this,
                "SOS",
                CallDetection.getStringValue(CallDetection.MEDICAL_GROUP_ID, this)!!,
                userRecieveData.userAuthkey.toString(),
                userRecieveData.userTripBy.toString(),
                userRecieveData.userLatitude.toString(),
                userRecieveData.userUserType.toString(),
                userRecieveData.userUser?.userFirstName.toString(),
                userRecieveData.userUser?.userLastName.toString(),
                userRecieveData.userUser?.userGender.toString(),
                userRecieveData.userUser?.userPhone.toString(),
                userRecieveData.userUser?.userEmail.toString(),
                userRecieveData.userLongitude.toString(),
                userRecieveData.userCaseType.toString(),
                userRecieveData.userVehicle?.userVehMake.toString(),
                userRecieveData.userVehicle?.userVehicleNumber.toString(),
                userRecieveData.userVehicle?.userVehicleModel.toString(),
                userRecieveData.userVehicle?.userVehYear.toString(),
                userRecieveData.userVehicle?.userVinNum.toString(),
                userRecieveData.userVehicle?.userVehicleType.toString()
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
                userRecieveData.userAuthkey.toString(),
                userRecieveData.userTripBy.toString(),
                userRecieveData.userLatitude.toString(),
                userRecieveData.userUserType.toString(),
                userRecieveData.userUser?.userFirstName.toString(),
                userRecieveData.userUser?.userLastName.toString(),
                userRecieveData.userUser?.userGender.toString(),
                userRecieveData.userUser?.userPhone.toString(),
                userRecieveData.userUser?.userEmail.toString(),
                userRecieveData.userLongitude.toString(),
                userRecieveData.userCaseType.toString(),
                userRecieveData.userVehicle?.userVehMake.toString(),
                userRecieveData.userVehicle?.userVehicleNumber.toString(),
                userRecieveData.userVehicle?.userVehicleModel.toString(),
                userRecieveData.userVehicle?.userVehYear.toString(),
                userRecieveData.userVehicle?.userVinNum.toString(),
                userRecieveData.userVehicle?.userVehicleType.toString()
            )
            CallDetection.setSBoolenVale(CallDetection.MEDICAL_CLICK, false, this)
            CallDetection.setSBoolenVale(CallDetection.ACCIDENT_CLICK, true, this)
        }
        if (v?.id == R.id.accident_sos) {

            CallDetection.sosApi(
                this,
                "SOS",
                CallDetection.getStringValue(CallDetection.ACCIDENT_GROUP_ID, this)!!,
                userRecieveData.userAuthkey.toString(),
                userRecieveData.userTripBy.toString(),
                userRecieveData.userLatitude.toString(),
                userRecieveData.userUserType.toString(),
                userRecieveData.userUser?.userFirstName.toString(),
                userRecieveData.userUser?.userLastName.toString(),
                userRecieveData.userUser?.userGender.toString(),
                userRecieveData.userUser?.userPhone.toString(),
                userRecieveData.userUser?.userEmail.toString(),
                userRecieveData.userLongitude.toString(),
                userRecieveData.userCaseType.toString(),
                userRecieveData.userVehicle?.userVehMake.toString(),
                userRecieveData.userVehicle?.userVehicleNumber.toString(),
                userRecieveData.userVehicle?.userVehicleModel.toString(),
                userRecieveData.userVehicle?.userVehYear.toString(),
                userRecieveData.userVehicle?.userVinNum.toString(),
                userRecieveData.userVehicle?.userVehicleType.toString()
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
                userRecieveData.userAuthkey.toString(),
                userRecieveData.userTripBy.toString(),
                userRecieveData.userLatitude.toString(),
                userRecieveData.userUserType.toString(),
                userRecieveData.userUser?.userFirstName.toString(),
                userRecieveData.userUser?.userLastName.toString(),
                userRecieveData.userUser?.userGender.toString(),
                userRecieveData.userUser?.userPhone.toString(),
                userRecieveData.userUser?.userEmail.toString(),
                userRecieveData.userLongitude.toString(),
                userRecieveData.userCaseType.toString(),
                userRecieveData.userVehicle?.userVehMake.toString(),
                userRecieveData.userVehicle?.userVehicleNumber.toString(),
                userRecieveData.userVehicle?.userVehicleModel.toString(),
                userRecieveData.userVehicle?.userVehYear.toString(),
                userRecieveData.userVehicle?.userVinNum.toString(),
                userRecieveData.userVehicle?.userVehicleType.toString()

            )
        }

    }
}