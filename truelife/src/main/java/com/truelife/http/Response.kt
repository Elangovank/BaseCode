package com.truelife.http

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by Mathan on 28/01/19.
 **/

open class Response : Serializable {

    @SerializedName("response_code")
    var responseCode: Int? = null

    @SerializedName("message")
    var responseMessage: String? = null

    @SerializedName("custom_message")
    var customMessage: String? = null
    /**
     * unique integer number for the request
     */
    @SerializedName("a1")
    var requestType: Int? = null

    @SerializedName("a2")
    var extraOutput: String? = null

    /**
     * @return true if the response gets success id
     */
    val isSuccess: Boolean
        get() = responseCode == 200

}
