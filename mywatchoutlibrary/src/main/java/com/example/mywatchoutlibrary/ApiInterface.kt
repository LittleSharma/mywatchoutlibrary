package com.example.watchoutemergencylibrary

import com.example.mywatchoutlibrary.CallDetection
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ApiInterface {

    @GET(CallDetection.GET_RESPONDER_TYPE)
    fun ResponderList(@Header("apiAuthrizationKey") apiAuthrizationKey: String?): Call<ResponderListDao?>?

    @POST(CallDetection.FIND_RESPONDER)
    fun FindRespondercCall(@Header("apiAuthrizationKey") apiAuthrizationKey: String,
                      @Body request:FindResponderRequest): Call<FindResponderResponse?>?

    @POST(CallDetection.FIND_RESPONDER)
    fun FindResponderSos(@Header("apiAuthrizationKey") apiAuthrizationKey: String,
                      @Body request:FindResponderRequest): Call<FindResponderSosResponse?>?

    @FormUrlEncoded
    @POST(CallDetection.AUDIO_CALL + "{responder_id}" + "/" + "{user_Id}")
    fun audioCallApi(
        @Header("apiAuthrizationKey") apiAuthrizationKey: String,
        @Path("responder_id") responder_id: String?,
        @Path("user_Id") userId: String?,
        @Field("vehicleId") vehicleId: String?,
        @Field("groupId") groupId: String?,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?
    ): Call<AudioCallResponse?>?

}