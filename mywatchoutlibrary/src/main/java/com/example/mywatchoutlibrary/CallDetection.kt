package com.example.mywatchoutlibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.mycalldetectionlibrary.ApiClient
import com.example.watchoutemergencylibrary.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CallDetection {

    val TAG = "Check_Permission"
    val RECORD_REQUEST_CODE = 101
    private var editor: SharedPreferences.Editor? = null
    private var preferences: SharedPreferences? = null
    lateinit var apiInterface: ApiInterface
    lateinit var apiClient: ApiClient
    var btnVisibilty = false
    const val BASE_URL = "https://app.watchout.ng/api/"
    //    const val BASE_URL = "http://192.168.1.145:4000/"
    const val GET_RESPONDER_TYPE = "third-party/get-responder-type"
    const val FIND_RESPONDER = "third-party/find-responder"
    const val AUDIO_CALL = "third-party/audio-call/"
    const val SECURITY_GROUP_ID = "security_group_id"
    const val MEDICAL_GROUP_ID = "medical_group_id"
    const val MEDICAL_USER_ID = "medical_user_id"
    const val MEDICAL_RESPONDER_ID = "medical_responder_id"
    const val MEDICAL_VEHICLE_ID = "medical_vehicle_id"
    const val ACCIDENT_GROUP_ID = "accident_group_id"
    const val ACCIDENT_USER_ID = "medical_user_id"
    const val ACCIDENT_RESPONDER_ID = "medical_responder_id"
    const val ACCIDENT_VEHICLE_ID = "medical_vehicle_id"
    const val SharedprefernceName = "UserData"
    const val MEDICAL_CLICK = "medClick"
    const val ACCIDENT_CLICK = "accClick"

    // Calling This Method in onRecieve Method of Broadcast Reciever
    // Give (contaxt , intent) 2 Argument of This Method

    @SuppressLint("LongLogTag")
    fun callDetection(context: Context?, intent: Intent?) {

        if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                .equals(TelephonyManager.EXTRA_STATE_OFFHOOK)
        ) {
            showToast(context!!, "Call started...")
            Log.d("Call Detection", "Outgoing Call Started---->>")

        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                .equals(TelephonyManager.EXTRA_STATE_IDLE)
        ) {
            showToast(context!!, "Call ended...")
            if (getBoolenValue(MEDICAL_CLICK,context)==true) {
                audioCallApi("1221117105526941",
                    getStringValue(MEDICAL_RESPONDER_ID, context)!!,
                    getStringValue(MEDICAL_USER_ID, context)!!,
                    getStringValue(MEDICAL_VEHICLE_ID, context)!!,
                    getStringValue(MEDICAL_GROUP_ID, context)!!, "75.7873", "26.9124")
                Log.d("Call Detection Medical Responder Id", getStringValue(MEDICAL_RESPONDER_ID, context)!!)
                Log.d("Call Detection Medical User Id", getStringValue(MEDICAL_USER_ID, context)!!)
                Log.d("Call Detection Medical Vehicle Id", getStringValue(MEDICAL_VEHICLE_ID, context)!!)
                Log.d("Call Detection Medical Group Id", getStringValue(MEDICAL_GROUP_ID, context)!!)
            }
            if (getBoolenValue(ACCIDENT_CLICK,context)==true) {
                audioCallApi("1221117105526941",
                    getStringValue(ACCIDENT_RESPONDER_ID, context)!!,
                    getStringValue(ACCIDENT_USER_ID, context)!!,
                    getStringValue(ACCIDENT_VEHICLE_ID, context)!!,
                    getStringValue(ACCIDENT_GROUP_ID, context)!!, "75.7873", "26.9124")
                Log.d("Call Detection Accident-- Responder Id", getStringValue(ACCIDENT_RESPONDER_ID, context)!!)
                Log.d("Call Detection Accident-- User Id", getStringValue(ACCIDENT_USER_ID, context)!!)
                Log.d("Call Detection Accident-- Vehicle Id", getStringValue(ACCIDENT_VEHICLE_ID, context)!!)
                Log.d("Call Detection Accident-- Group Id", getStringValue(ACCIDENT_GROUP_ID, context)!!)
            }

        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                .equals(TelephonyManager.EXTRA_STATE_RINGING)
        ) {
            showToast(context!!, "Incoming call...");
            Log.d("Call Detection", "Incoming Call---->>")
        }
    }

    // Using this Function for Display All Watchout Groups

    fun emergencyUiDetails(context: Context, authKey : String, medicalBtn:Button, accidentBtn:Button, securityBtn:Button) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
        var call: Call<ResponderListDao?>? = apiInterface.ResponderList(authKey)
        call?.enqueue(object : Callback<ResponderListDao?> {
            override fun onResponse(
                call: Call<ResponderListDao?>,
                response: Response<ResponderListDao?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("Response-->>", response.body().toString())
                    medicalBtn.setText(response.body()!!.data!![0].group_name)
                    accidentBtn.setText(response.body()!!.data!![1].group_name)
                    securityBtn.setText(response.body()!!.data!![2].group_name)
                    setStringVale(MEDICAL_GROUP_ID, response.body()!!.data!![0]._id!!, context)
                    setStringVale(ACCIDENT_GROUP_ID, response.body()!!.data!![1]._id!!, context)
                    setStringVale(SECURITY_GROUP_ID, response.body()!!.data!![2]._id!!, context)
                } else Log.d("Error Occure-->>", "Response Not Found")
            }

            override fun onFailure(call: Call<ResponderListDao?>, t: Throwable) {
                call.cancel()
                Log.d("Error Occure-->>", t.message!!)
            }
        })
    }

    // Using medicalAccidentCallApi

    fun medicalAccidentCallApi(context: Context, CallIncomingType: String, groupId: String, authKey: String, tripBy:String, latitude:String,
    userType:String, firstName:String, lastName:String, gender:String, phone:String, email:String, longitude:String, caseType:String, vehMake:String,
    vehicleNumber:String, vehicleModel:String, vehYear:String, vinNum:String, vehicleType:String) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
        var call: Call<FindResponderResponse?>? = apiInterface.FindRespondercCall(
            authKey, FindResponderRequest(
                CallIncomingType,
                tripBy, latitude, groupId, userType, User(
                    firstName, lastName, gender,
                    phone, email
                ), longitude, caseType, Vehicle(
                    vehMake, vehicleNumber,
                    vehicleModel, vehYear, vinNum, vehicleType
                )
            )
        )
        call!!.enqueue(object : Callback<FindResponderResponse?> {
            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<FindResponderResponse?>,
                response: Response<FindResponderResponse?>
            ) {
                if (response.body()!!.responder != null) {
                    if (response.body()!!.responder!!.groupId != null) {
                        if (response.body()!!.responder!!.groupId == getStringValue(MEDICAL_GROUP_ID, context)) {
                            val number: String = response.body()!!.responder!!.responderPhone!!.toString()
                            openDialer(context, number)
                            setStringVale(MEDICAL_RESPONDER_ID, response.body()!!.responder!!.responderId!!, context)
                            Log.d("Medical Responder Id", getStringValue(MEDICAL_RESPONDER_ID, context)!!)
                            setStringVale(MEDICAL_USER_ID, response.body()!!.userId!!, context)
                            Log.d("Medical User Id", getStringValue(MEDICAL_USER_ID, context)!!)
                            setStringVale(MEDICAL_VEHICLE_ID, response.body()!!.vehicleId!!, context)
                            Log.d("Medical Vehicle Id", getStringValue(MEDICAL_VEHICLE_ID, context)!!)
                            Log.d("Medical Group Id", getStringValue(MEDICAL_GROUP_ID, context)!!)
                            Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                        if (response.body()!!.responder!!.groupId == getStringValue(ACCIDENT_GROUP_ID, context)) {
                            val number: String = response.body()!!.responder!!.responderPhone!!.toString()
                            openDialer(context, number)
                            setStringVale(ACCIDENT_RESPONDER_ID, response.body()!!.responder!!.responderId!!, context)
                            Log.d("Accident Responder Id", getStringValue(ACCIDENT_RESPONDER_ID, context)!!)
                            setStringVale(ACCIDENT_USER_ID, response.body()!!.userId!!, context)
                            Log.d("Accident User Id", getStringValue(ACCIDENT_USER_ID, context)!!)
                            setStringVale(ACCIDENT_VEHICLE_ID, response.body()!!.vehicleId!!, context)
                            Log.d("Accident Vehicle Id", getStringValue(ACCIDENT_VEHICLE_ID, context)!!)
                            Log.d("Accident Group Id", getStringValue(ACCIDENT_GROUP_ID, context)!!)
                            Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    else { Toast.makeText(context, "Responder Not Found", Toast.LENGTH_SHORT).show() }
                }
                else { Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show() }
            }

            override fun onFailure(call: Call<FindResponderResponse?>, t: Throwable) {
                call.cancel()
                Log.d("Error Occure-->>", t.message!!)
            }
        })
    }

    // Using securityCallApi

    fun securityCallApi(context: Context, CallIncomingType: String, groupId: String, authKey: String, tripBy:String, latitude:String,
                        userType:String, firstName:String, lastName:String, gender:String, phone:String, email:String, longitude:String, caseType:String, vehMake:String,
                        vehicleNumber:String, vehicleModel:String, vehYear:String, vinNum:String, vehicleType:String ) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
        var call: Call<FindResponderResponse?>? = apiInterface.FindRespondercCall(
            authKey, FindResponderRequest(
                CallIncomingType,
                tripBy, latitude, groupId, userType, User(
                    firstName, lastName, gender,
                    phone, email
                ), longitude, caseType, Vehicle(
                    vehMake, vehicleNumber,
                    vehicleModel, vehYear, vinNum, vehicleType
                )
            )
        )
        call!!.enqueue(object : Callback<FindResponderResponse?> {
            override fun onResponse(
                call: Call<FindResponderResponse?>,
                response: Response<FindResponderResponse?>
            ) {
                if (response.body()!!.responder != null) {
                    if (response.body()!!.responder!!.emergencyContact != null) {
                        val number: String = response.body()!!.responder!!.emergencyContact!!.toString()
                        openDialer(context, number)
                        Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Security Number Not Found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Security Number Not Found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FindResponderResponse?>, t: Throwable) {
                call.cancel()
                Log.d("Error Occure-->>", t.message!!)
            }
        })
    }

    // Using sosApi

    fun sosApi(context: Context, CallIncomingType: String, groupId: String, authKey: String, tripBy:String, latitude:String,
               userType:String, firstName:String, lastName:String, gender:String, phone:String, email:String, longitude:String, caseType:String, vehMake:String,
               vehicleNumber:String, vehicleModel:String, vehYear:String, vinNum:String, vehicleType:String ) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
        var call: Call<FindResponderSosResponse?>? = apiInterface.FindResponderSos(
            authKey, FindResponderRequest(
                CallIncomingType,
                tripBy, latitude, groupId, userType, User(
                    firstName, lastName, gender,
                    phone, email
                ), longitude, caseType, Vehicle(
                    vehMake, vehicleNumber,
                    vehicleModel, vehYear, vinNum, vehicleType
                )
            )
        )
        call!!.enqueue(object : Callback<FindResponderSosResponse?> {
            override fun onResponse(
                call: Call<FindResponderSosResponse?>,
                response: Response<FindResponderSosResponse?>
            ) {
                Log.d("Status", response.body()!!.status!!)
                Log.d("Message", response.body()!!.message!!)
                Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<FindResponderSosResponse?>, t: Throwable) {
                call.cancel()
                Log.d("Error Occure-->>", t.message!!)
            }
        })
    }

    // Using audioCallApi

    fun audioCallApi(authKey: String, responderId: String, userId: String, vehichleId: String, groupId: String, latitude: String, longitude: String) {
        apiClient = ApiClient()
        apiInterface = apiClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)
        val call: Call<AudioCallResponse?>? = apiInterface.audioCallApi(
            authKey, responderId, userId, vehichleId, groupId, latitude, longitude)
        call!!.enqueue(object : Callback<AudioCallResponse?> {
            override fun onResponse(
                call: Call<AudioCallResponse?>,
                response: Response<AudioCallResponse?>
            ) {
            }

            override fun onFailure(call: Call<AudioCallResponse?>, t: Throwable) {
                call.cancel()
                Log.d("Error Occure-->>", t.message!!)
            }
        })
    }


    // Using Custom Toast

    fun showToast(context: Context, message: String) {
        var toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    // Using Open Dialer

    fun openDialer(context: Context, number: String) {
        val intent: Intent
        intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse("tel:" + number))
        startActivity(context, intent, null)
    }

    // Calling This Method for Self Check Permission of Main Activity

    fun setupPermissions(context: Context) {

        val permission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.READ_PHONE_STATE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequest(context)
        }
    }

    fun makeRequest(context: Context) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.READ_PHONE_STATE),
            RECORD_REQUEST_CODE
        )
    }

    // Using initialiseEditor Preference

    @SuppressLint("CommitPrefEdits")
    private fun initialiseEditor(cntx: Context) {
        preferences = cntx.getSharedPreferences(SharedprefernceName, Context.MODE_PRIVATE);
        editor = preferences!!.edit()

    }

    // Using commitEditor Preference

    private fun commitEditor() {
        editor!!.commit()
    }

    // Using setStringVale Preference

    @SuppressLint("CommitPrefEdits")
    fun setStringVale(key: String, value: String, cntx: Context) {
        initialiseEditor(cntx)

        editor!!.putString(key, value)

        commitEditor()

    }

    // Using setSBoolenVale Preference

    @SuppressLint("CommitPrefEdits")
    fun setSBoolenVale(key: String, value: Boolean, cntx: Context) {

        editor!!.putBoolean(key, value)

        commitEditor()
    }

    // Using getStringValue Preference

    fun getStringValue(key: String, cntx: Context): String? {
        initialiseEditor(cntx)

        return preferences?.getString(key, "null")
    }

    // Using getBoolenValue Preference

    fun getBoolenValue(key: String, cntx: Context): Boolean? {
        initialiseEditor(cntx)

        return preferences?.getBoolean(key, false)
    }

    // Using Medical Button Visibility

    fun medicalVisibility(medicalCall:Button, medicalSos:Button) {

        if (!btnVisibilty) {
            medicalCall.setVisibility(View.VISIBLE)
            medicalSos.setVisibility(View.VISIBLE)
        } else {
            medicalCall.setVisibility(View.GONE)
            medicalSos.setVisibility(View.GONE)
        }
        btnVisibilty = !btnVisibilty

    }

    // Using Accident Button Visibility

    fun accidentVisibility(accidentCall:Button, accidentSos:Button) {

        if (!btnVisibilty) {
            accidentCall.setVisibility(View.VISIBLE)
            accidentSos.setVisibility(View.VISIBLE)
        } else {
            accidentCall.setVisibility(View.GONE)
            accidentSos.setVisibility(View.GONE)
        }
        btnVisibilty = !btnVisibilty
    }

    // Using Security Button Visibility

    fun securityVisibility(securityCall:Button) {

        if (!btnVisibilty) {
            securityCall.setVisibility(View.VISIBLE)
        } else {
            securityCall.setVisibility(View.GONE)
        }
        btnVisibilty = !btnVisibilty

    }


}