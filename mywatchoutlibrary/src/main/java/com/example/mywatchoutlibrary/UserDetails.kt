package com.example.mywatchoutlibrary

import com.google.gson.annotations.SerializedName

data class UserDetails(

	@field:SerializedName("apiAuthrizationKey")
	val userAuthkey: String? = null,

	@field:SerializedName("longitude")
	val userLongitude: Any? = null,

	@field:SerializedName("latitude")
	val userLatitude: Any? = null,

	@field:SerializedName("tripBy")
	val userTripBy: String? = null,

	@field:SerializedName("userType")
	val userUserType: String? = null,

	@field:SerializedName("user")
	val userUser: UserUser? = null,

	@field:SerializedName("caseType")
	val userCaseType: String? = null,

	@field:SerializedName("vehicle")
	val userVehicle: UserVehicle? = null,

	@field:SerializedName("caseIncomingType")
	val userCaseIncomingType: String? = null,
)

data class UserUser(

	@field:SerializedName("firstName")
	val userFirstName: String? = null,

	@field:SerializedName("lastName")
	val userLastName: String? = null,

	@field:SerializedName("email")
	val userEmail: String? = null,

	@field:SerializedName("phone")
	val userPhone: String? = null,

	@field:SerializedName("gender")
	val userGender: String? = null
)

data class UserVehicle(

	@field:SerializedName("vehYear")
	val userVehYear: String? = null,

	@field:SerializedName("vehMake")
	val userVehMake: String? = null,

	@field:SerializedName("vinNum")
	val userVinNum: String? = null,

	@field:SerializedName("vehicle_number")
	val userVehicleNumber: String? = null,

	@field:SerializedName("vehicle_model")
	val userVehicleModel: String? = null,

	@field:SerializedName("vehicleType")
	val userVehicleType: String? = null
)
