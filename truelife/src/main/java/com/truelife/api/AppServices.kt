package com.truelife.api

import android.content.Context
import com.android.volley.Request
import com.truelife.BuildConfig
import com.truelife.app.model.User
import com.truelife.http.JsonRestClient
import com.truelife.http.ResponseListener
import com.truelife.http.RestClient
import com.truelife.storage.LocalStorageSP
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Created by Mathan on 3/3/18.
 **/

class AppServices {

    object API {

        // No Prefix
        fun constructUrl(urlKey: String): String {
            return String.format("%s%s", BuildConfig.API_URL, urlKey)
        }

        // With Prefix
        fun constructSuffixUrl(c: Context, urlKey: String): String {
            val isProvider = LocalStorageSP[c, "is_provider", false]!!
            val SUFFIX = String.format("api/v1/%s/", if (isProvider) "providers" else "patients")
            return String.format("%s%s%s", BuildConfig.API_URL, SUFFIX, urlKey)
        }

        // WebSocket URL
        fun constructSocketUrl(c: Context): String {
            return String.format(
                "%s%s",
                BuildConfig.SOCKET_URL,
                String.format("cable?token=%s", LocalStorageSP[c, "auth_key", ""]!!)
            )
        }

        // API Case's

        // Login Screen
        val login = "login"
        val resetPassword = "passwords/send_email"
        val logout = "logout"
        val register = "patients"
    }

    private interface ApiInterface {

        // ******* Update Family Member ******* //

        @Multipart
        @POST
        fun updateMember(
            @Url url: String,
            @HeaderMap mHeader: HashMap<String, String>,
            @Part file: MultipartBody.Part?,
            @QueryMap mParam: HashMap<String, String>
        ): Call<ResponseBody>

        @POST
        fun updateMember(
            @Url url: String,
            @HeaderMap mHeader: HashMap<String, String>,
            @QueryMap mParam: HashMap<String, String>
        ): Call<ResponseBody>

    }


    companion object {

        private var retrofit: Retrofit? = null

        private fun fillCommons(c: Context, client: RestClient) {
            client.addHeader("Authorization", LocalStorageSP[c, "auth_key", ""]!!)
        }

        private fun fillCommons(c: Context, client: JsonRestClient) {
            client.header("Authorization", LocalStorageSP[c, "auth_key", ""]!!)
            client.header("Content-Type", "application/json")
        }

        private fun getClient(): Retrofit {

            val okHttpClient = OkHttpClient.Builder().build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit as Retrofit
        }

        fun login(c: Context, email: String, password: String): Boolean {
            try {
                // Generating Req
                val obj = JSONObject()
                obj.put("email", email)
                obj.put("password", password)
                val client = JsonRestClient(c, Request.Method.POST, API.constructUrl(API.login), API.login.hashCode())
                client.header("Content-Type", "application/json; charset=utf-8")
                client.execute(c as ResponseListener, obj, User::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
            return true
        }
    }
}