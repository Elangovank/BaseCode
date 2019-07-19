package com.apartment.app.model

import com.apartment.http.Response
import com.google.gson.annotations.SerializedName

/**
 * Created by Mathan on 28/01/19.
 **/

class User : Response() {

    @SerializedName("auth_token")
    var auth_token: String? = null

    @SerializedName("patient_profile")
    var patient_profile: User? = null

    @SerializedName("profile_picture")
    var profile_picture: String? = null

    @SerializedName("user_mail")
    var user_mail: String? = null

    @SerializedName("user_info")
    var user_info: User? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("first_name")
    var first_name: String? = null

    @SerializedName("last_name")
    var last_name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("birthday")
    var birthday: String? = null

    @SerializedName("sex")
    var sex: String? = null

    @SerializedName("occupation")
    var occupation: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("address")
    var address: User? = null

    @SerializedName("street_one")
    var street_one: String? = null

    @SerializedName("street_two")
    var street_two: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("zip")
    var zip: String? = null

    @SerializedName("age")
    var age: String? = null

    @SerializedName("qualifications")
    var qualification: String? = null

    @SerializedName("special_qualifications")
    var certification: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("experience")
    var experience: Int? = null

    @SerializedName("npi")
    var npi: String? = null

    @SerializedName("state_of_license")
    var license: String? = null

    @SerializedName("time_zone")
    var time_zone: String? = null

    @SerializedName("program_provider_id")
    var program_provider_id: Int? = null

    @SerializedName("is_provider")
    var is_provider: Boolean = false

    @SerializedName("term_accepted")
    var term_accepted: Boolean = false

    @SerializedName("complete_profile")
    var complete_profile: Boolean = false

    @SerializedName("board_certified")
    var board_certified: Boolean = false

    @SerializedName("terms")
    var terms: String? = null

}
