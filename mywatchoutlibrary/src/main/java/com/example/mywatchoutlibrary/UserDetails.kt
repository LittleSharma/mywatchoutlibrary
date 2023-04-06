package com.example.mywatchoutlibrary

import com.google.gson.annotations.SerializedName

data class UserDetails(

	@field:SerializedName("user_user")
	val userUser: UserUser? = null,

	@field:SerializedName("user_tripBy")
	val userTripBy: String? = null,

	@field:SerializedName("user_authkey")
	val userAuthkey: String? = null,

	@field:SerializedName("user_caseType")
	val userCaseType: String? = null,

	@field:SerializedName("user_userType")
	val userUserType: String? = null,

	@field:SerializedName("user_vehicle")
	val userVehicle: UserVehicle? = null,

	@field:SerializedName("user_longitude")
	val userLongitude: Any? = null,

	@field:SerializedName("user_caseIncomingType")
	val userCaseIncomingType: String? = null,

	@field:SerializedName("user_latitude")
	val userLatitude: Any? = null
)

data class UserUser(

	@field:SerializedName("user_email")
	val userEmail: String? = null,

	@field:SerializedName("user_firstName")
	val userFirstName: String? = null,

	@field:SerializedName("user_lastName")
	val userLastName: String? = null,

	@field:SerializedName("user_phone")
	val userPhone: String? = null,

	@field:SerializedName("user_gender")
	val userGender: String? = null
)

data class UserVehicle(

	@field:SerializedName("user_vinNum")
	val userVinNum: String? = null,

	@field:SerializedName("user_vehMake")
	val userVehMake: String? = null,

	@field:SerializedName("user_vehicle_number")
	val userVehicleNumber: String? = null,

	@field:SerializedName("user_vehYear")
	val userVehYear: String? = null,

	@field:SerializedName("user_vehicle_model")
	val userVehicleModel: String? = null,

	@field:SerializedName("user_vehicleType")
	val userVehicleType: String? = null
)
