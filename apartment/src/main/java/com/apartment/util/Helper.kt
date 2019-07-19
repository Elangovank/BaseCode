package com.apartment.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import android.util.Patterns
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Mathan on 31/01/19.
 **/

object Helper {

    fun getTimeZone(): ArrayList<String>? {
        val timezones = TimeZone.getAvailableIDs()
        val list = ArrayList<String>()

        for (i in timezones.indices) {
            val zone = TimeZone.getTimeZone(timezones[i]).getDisplayName(true, TimeZone.SHORT)
            val name = timezones[i]

            val out = String.format("(%s) %s", zone, name)
            list.add(out)
        }
        return list
    }

    fun isValidEmail(target: String): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isValidMobileNo(target: String): Boolean {
        return !TextUtils.isEmpty(target) && target.length == 10
    }

    /**
     * @param context Context
     */
    fun navigateAppSetting(context: Context) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)
    }

    /**
     * @param context Context
     */
    fun navigatePlaystore(context: Context) {
        val appPackageName = context.packageName
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}

