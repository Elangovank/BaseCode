package com.apartment.util

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.apartment.R
import com.apartment.api.AppServices
import com.apartment.app.constants.Constant
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.text.DecimalFormat


/**
 * Created by Mathan on 31/01/19.
 **/

object Utility {
    private val df = DecimalFormat("#.###")
    private val dfSingle = DecimalFormat("#.#")
    private val dfMoney = DecimalFormat("#.00")

    fun formatDecimal(value: Double): String {
        return df.format(value)
    }

    fun formatDecimalToSingleDigit(value: Double): String {
        return dfSingle.format(value)
    }

    fun formatDecimalToMoney(value: Double): String {
        return dfMoney.format(value)
    }

    @SuppressLint("MissingPermission")
    fun isInternetAvailable(context: Context?): Boolean {
        try {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo: NetworkInfo?
            netInfo = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return false
    }

    /**
     * Get color value according to the version compat
     *
     * @param c     Context
     * @param color color resource int sample R.color.accent
     * @return Parsed int value equal to [android.graphics.Color] values
     */
    fun getColor(c: Context, color: Int): Int {
        return ContextCompat.getColor(c, color)
    }

    /**
     * Get color value according to the version compat
     *
     * @param c  Context
     * @param id drawable resource int sample R.drawable.rounded_bg
     * @return Parsed int value equal to [android.graphics.Color] values
     */
    fun getDrawable(c: Context, id: Int): Drawable {
        return ContextCompat.getDrawable(c, id)!!
    }

    fun errorText(text: String): String? {
        return String.format("%s should not be empty", text)

    }

    fun log(msg: String) {
        Log.d("FFFFFFFF ---> ", msg)
    }

    fun get_roundImage(c: Context, bitmap: Bitmap): Bitmap? {
        try {
            val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val rect = Rect(0, 0, bitmap.width, bitmap.height)

            val paint = Paint()
            paint.isAntiAlias = true

            val canvas = Canvas(output)
            canvas.drawARGB(0, 0, 0, 0)
            canvas.drawCircle(
                (bitmap.width / 2).toFloat(),
                (bitmap.height / 2).toFloat(),
                (bitmap.width / 2).toFloat(),
                paint
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

            canvas.drawBitmap(bitmap, rect, rect, paint)

            return output

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun encodeFileToBase64Binary(filePath: String): String? {
        var encodedfile: String? = null
        try {
            val file = File(filePath)
            val fileInputStreamReader = FileInputStream(file)
            val bytes = ByteArray(file.length().toInt())
            fileInputStreamReader.read(bytes)
            encodedfile = Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return encodedfile
    }

    fun decodeFileToBase64Binary(data: String): Bitmap? {
        val decodedString = Base64.decode(data, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        return decodedByte
    }

    fun colorString(text: String): String? {
        return String.format("<b>%s</b>", text)
    }

    fun noColorString(text: String): String? {
        return String.format("%s", text)
    }

    fun getFirstLetterCaps(text: String): String? {
        try {
            val output = String.format("%s", text.substring(0, 1).toUpperCase() + text.substring(1))
            return output
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun stripNonDigits(input: CharSequence): String {
        val sb = StringBuilder(input.length)
        (0 until input.length).map { input[it] }.filter { it.toInt() in 48..57 }.forEach { sb.append(it) }
        return sb.toString()
    }

    fun isAppRunning(context: Context): Boolean {
        var isInBackground = true
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            val runningProcesses = am.runningAppProcesses
            for (processInfo in runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (activeProcess in processInfo.pkgList) {
                        if (activeProcess == context.packageName) {
                            isInBackground = false
                        }
                    }
                }
            }
        } else {
            val taskInfo = am.getRunningTasks(1)
            val componentInfo = taskInfo.get(0).topActivity
            if (componentInfo.packageName.equals(context.packageName)) {
                isInBackground = false
            }
        }
        return isInBackground
    }

    /**
     * Load images
     */
    fun loadImage(aURL: String, image: ImageView) {
        try {
            if (aURL.contains("rails/")) {
                Picasso.get().load(AppServices.API.constructUrl(aURL))
                    .placeholder(R.drawable.ic_user_placeholder)
                    .error(R.drawable.ic_user_placeholder)
                    .into(image)
            } else {
                Picasso.get().load(File(aURL))
                    .placeholder(R.drawable.ic_user_placeholder)
                    .error(R.drawable.ic_user_placeholder)
                    .into(image)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Show Date Picker
     */

    fun showDatePicker(context: Context, view: TextView, format: String, hidePast: Boolean) {
        val datePicker = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val date = String.format("%s-%s-%s", datePicker.year, datePicker.month + 1, datePicker.dayOfMonth)
            view.text = DateUtil.convertDateFormat(date, Constant.YY_MM_DD, format)
        }

        if (view.text.isEmpty())
            DateUtil.showDateDialog(context, datePicker, hidePast)
        else DateUtil.showFromDateDialog(
            context,
            DateUtil.convertDateFormat(
                view.text.toString(),
                format,
                Constant.DD_MM_YY
            ), datePicker, hidePast
        )
    }


    /**
     * Show Time Picker
     */

    fun showTimePicker(context: Context, view: TextView, isCancelable: Boolean) {
        val array = DateUtil.getTimeSlot(context, Constant.HH_MM_AA, 30)
        AppDialogs.showSingleChoice(
            context,
            context.getString(R.string.choose_time_caps),
            array.toTypedArray(),
            object : SingleChoiceAdapter.Callback {
                override fun info(position: Int, text: String) {
                    AppDialogs.hideSingleChoice()
                    view.text = text
                }
            },
            isCancelable
        )
    }

    /**
     * Show Time Picker
     */

    fun showTimePicker(context: Context, view: TextView, array: ArrayList<String>, isCancelable: Boolean) {
        AppDialogs.showSingleChoice(
            context,
            context.getString(R.string.choose_time_caps),
            array.toTypedArray(),
            object : SingleChoiceAdapter.Callback {
                override fun info(position: Int, text: String) {
                    AppDialogs.hideSingleChoice()
                    view.text = text
                }
            },
            isCancelable
        )
    }
}

