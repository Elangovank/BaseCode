package com.apartment.util

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.apartment.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*


/**
 * Created by Mathan on 31/01/19.
 **/


object AppDialogs {

    private var progressDialog: Dialog? = null
    private var dialog: Dialog? = null
    private var custom_dialog: Dialog? = null
    private var selection_dialog: Dialog? = null
    private var bottom_dialog: BottomSheetDialog? = null

    /**
     * Simple interface can be implemented for confirm an action via dialogs
     */
    interface ConfirmListener {
        fun yes()
    }

    interface OptionListener : ConfirmListener {
        fun no()
    }

    /**
     * Confirm actions that are critical before proceeding
     *
     * @param c
     * @param text
     * @param l
     */
    @SuppressLint("InlinedApi")
    fun optionalAction(c: Context, text: String, l: OptionListener?, yes: String? = "YES", no: String? = "NO") {
        val builder = AlertDialog.Builder(c, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
        builder.setTitle(c.resources.getString(R.string.app_name))
        builder.setMessage(text)

        builder.setPositiveButton(yes) { dialog, _ ->
            l?.yes()
            dialog.dismiss()
        }

        builder.setNegativeButton(no) { dialog, _ ->
            l?.no()
            dialog.dismiss()
        }

        builder.setOnCancelListener { dialogInterface -> dialogInterface.dismiss() }
        builder.create().show()
    }


    /**
     * Confirm actions that are critical before proceeding
     *
     * @param c
     * @param text
     * @param l
     */
    @SuppressLint("InlinedApi")
    fun confirmAction(c: Context, title: String, text: String, l: ConfirmListener?) {
        try {
            val alertDialog: AlertDialog
            val builder = AlertDialog.Builder(c, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
            builder.setTitle(title)
            builder.setMessage(text)

            builder.setPositiveButton(c.resources.getString(android.R.string.yes)) { dialog, which ->
                l?.yes()
                dialog.dismiss()
            }
            builder.setNegativeButton(c.resources.getString(android.R.string.no)) { dialog, which -> dialog.dismiss() }
            builder.setOnCancelListener { dialogInterface -> dialogInterface.dismiss() }
            alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Confirm actions that are critical before proceeding
     *
     * @param c
     * @param text
     */
    @SuppressLint("InlinedApi")
    fun okAction(c: Context, text: String) {
        val alertDialog: AlertDialog
        val builder = AlertDialog.Builder(c, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
        builder.setTitle(c.resources.getString(R.string.app_name))
        builder.setMessage(text)

        builder.setPositiveButton(c.resources.getString(android.R.string.ok)) { dialog, which -> dialog.dismiss() }

        builder.setOnCancelListener { dialogInterface -> dialogInterface.dismiss() }
        alertDialog = builder.create()
        alertDialog.show()

    }


    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun showProgressDialog(context: Context) {
        hideProgressDialog()
        progressDialog = Dialog(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_progress_custom, null)

        val progressBar = view.findViewById<ProgressBar>(R.id.dialog_progress_bar)
        progressBar.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.app_blue_light), PorterDuff.Mode.SRC_IN
        )

        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        progressDialog!!.setContentView(view)
        progressDialog!!.setCancelable(true)
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("InlinedApi")
    fun showcustomView(context: Context, layout: Int, isCancelable: Boolean): View? {
        try {
            hidecustomView()
            custom_dialog = Dialog(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
            custom_dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            custom_dialog!!.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(layout, null)
            custom_dialog!!.setContentView(view)
            custom_dialog!!.setCancelable(isCancelable)
            custom_dialog!!.show()
            return view
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Bottom Dialog
     */
    fun showBottomView(context: Context, layout: Int, isCancelable: Boolean): View? {
        try {
            hidecustomView()
            bottom_dialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
            val view = LayoutInflater.from(context).inflate(layout, null)
            bottom_dialog!!.setContentView(view)
            bottom_dialog!!.setCancelable(isCancelable)
            bottom_dialog!!.show()
            return view
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Show Ok Action Dialog
     * @param context Context
     * @param title Title
     * @param msg Message
     * @param action Positive Button Text
     * @param l ConfirmListener
     * @param isCancelable Close button view or hide
     */

    fun customOkAction(
        context: Context,
        title: String?,
        msg: String,
        action: String?,
        l: ConfirmListener?,
        isCancelable: Boolean
    ) {

        hidecustomView()

        val builder = AlertDialog.Builder(context, R.style.BottomSheetDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_ok_action, null)

        val dialogTitle = view.findViewById(R.id.dialog_title) as TextView
        val dialogMessage = view.findViewById(R.id.dialog_message) as TextView
        val dialogAction = view.findViewById(R.id.dialog_action_button) as TextView
        val dialogClose = view.findViewById(R.id.dialog_close_button) as ImageView

        builder.setCancelable(false)

        if (isCancelable)
            dialogClose.visibility = View.VISIBLE
        else dialogClose.visibility = View.GONE

        dialogTitle.text = if ((title == null)) context.getString(R.string.app_name) else title
        dialogMessage.text = msg

        dialogAction.text = if ((action == null)) context.getString(R.string.ok) else action
        dialogAction.setOnClickListener {
            l?.yes()
            custom_dialog!!.dismiss()
        }

        dialogClose.setOnClickListener {
            custom_dialog!!.dismiss()
        }

        builder.setView(view)
        custom_dialog = builder.create()
        custom_dialog!!.show()
    }

    /**
     * Show Double Action Dialog
     * @param context Context
     * @param title Title
     * @param msg Message
     * @param Positiveaction Positive Button Text
     * @param Negativeaction Negative Button Text
     * @param l OptionListener
     * @param isCancelable Close button view or hide
     * @param isOptionable Negative button background gray
     */

    fun customDoubleAction(
        context: Context,
        title: String?,
        msg: String,
        Positiveaction: String?,
        Negativeaction: String?,
        l: OptionListener?,
        isCancelable: Boolean,
        isOptionable: Boolean
    ) {

        hidecustomView()

        val builder = AlertDialog.Builder(context, R.style.BottomSheetDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_double_action, null)

        val dialogTitle = view.findViewById(R.id.dialog_title) as TextView
        val dialogMessage = view.findViewById(R.id.dialog_message) as TextView
        val dialogActionPositive = view.findViewById(R.id.dialog_action_positive) as TextView
        val dialogActionNegative = view.findViewById(R.id.dialog_action_negative) as TextView
        val dialogClose = view.findViewById(R.id.dialog_close_button) as ImageView

        builder.setCancelable(false)

        dialogTitle.text = if ((title == null)) context.getString(R.string.app_name) else title
        dialogMessage.text = msg

        dialogActionPositive.text = if ((Positiveaction == null)) context.getString(R.string.ok) else Positiveaction
        dialogActionPositive.setOnClickListener {
            l?.yes()
            custom_dialog!!.dismiss()
        }

        if (isOptionable) {
            dialogActionNegative.setBackgroundResource(R.drawable.bg_circle_blue)
        }

        dialogActionNegative.text = if ((Negativeaction == null)) context.getString(R.string.cancel) else Negativeaction
        dialogActionNegative.setOnClickListener {
            l?.no()
            custom_dialog!!.dismiss()
        }

        if (isCancelable)
            dialogClose.visibility = View.VISIBLE
        else dialogClose.visibility = View.GONE

        dialogClose.setOnClickListener {
            custom_dialog!!.dismiss()
        }

        builder.setView(view)
        custom_dialog = builder.create()
        custom_dialog!!.show()
    }

    fun hidecustomView() {
        if (custom_dialog != null && custom_dialog!!.isShowing) {
            custom_dialog!!.dismiss()
        }
    }

    /**
     * @param context
     */
    fun hidecustomProgressDialog() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    /**
     * Hides the soft keyboard
     *
     * @param activity Activity
     */
    fun hideSoftKeyboard(activity: Activity, view: View) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Shows the soft keyboard
     *
     * @param a    Activity
     * @param view current EditText view
     */
    fun showSoftKeyboard(a: Activity, view: View) {
        val inputMethodManager = a.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }

    fun showSnackbar(
        activity: Activity,
        view: View,
        msg: String,
        action: String? = activity.resources.getString(R.string.settings)
    ) {
        val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(action) {
            startActivityForResult(activity, Intent(android.provider.Settings.ACTION_SETTINGS), 0, null)
        }
        snackbar.setActionTextColor(Utility.getColor(activity, R.color.app_green_light))
        snackbar.show()
    }

    fun showSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * showCustomSnackbar
     */
    fun showCustomSnackbar(context: Context, view: View, msg: String) {
        val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
        val layout = snackbar.view as Snackbar.SnackbarLayout
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val snackView = inflater.inflate(R.layout.inflate_custom_snackbar, null)

        val mLayout = snackView.findViewById<LinearLayout>(R.id.inflate_snackbar_layout)
        val mImage = snackView.findViewById<ImageView>(R.id.inflate_snackbar_image)
        val mText = snackView.findViewById<TextView>(R.id.inflate_snackbar_text)

        mText.text = msg

        layout.setPadding(0, 0, 0, 0)
        layout.addView(snackView, 0)
        snackbar.show()
    }

    /**
     *Short Toast
     */
    fun showToastshort(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     *Long Toast
     */
    fun showToastlong(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


    fun showFromDatedialogwithToday(context: Context, datepickListner: DatePickerDialog.OnDateSetListener) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(context, datepickListner, year, month, dayOfMonth)
        dialog.datePicker.minDate = System.currentTimeMillis() - 60 * 60 * 1000
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "SELECT", dialog)
        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "", dialog)
        dialog.setTitle("")
        dialog.show()
    }

    @SuppressLint("InlinedApi")
    fun showTimedialog(context: Context, timepickListner: TimePickerDialog.OnTimeSetListener) {

        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(
            context,
            android.R.style.Theme_DeviceDefault_Light_Dialog_Alert,
            timepickListner,
            hourOfDay,
            minute,
            false
        )
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "SELECT", dialog)
        dialog.setTitle("")
        dialog.show()
    }

    /**
     * Single Choice Selection
     */
    fun showSingleChoice(
        context: Context,
        title: String?,
        array: Array<String>,
        callback: SingleChoiceAdapter.Callback,
        isCancelable: Boolean
    ) {

        val builder = AlertDialog.Builder(context, R.style.BottomSheetDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_single_selection, null)

        val dialogTitle = view.findViewById(R.id.dialog_title) as TextView
        val dialogRecyclerView = view.findViewById(R.id.dialog_recycler) as RecyclerView
        val dialogClose = view.findViewById(R.id.dialog_close_button) as ImageView

        dialogRecyclerView.layoutManager = LinearLayoutManager(context)
        dialogRecyclerView.adapter = SingleChoiceAdapter(context, array, callback)

        builder.setCancelable(false)

        if (isCancelable)
            dialogClose.visibility = View.VISIBLE
        else dialogClose.visibility = View.GONE

        dialogTitle.text = if ((title == null)) context.getString(R.string.app_name) else title

        dialogClose.setOnClickListener {
            selection_dialog!!.dismiss()
        }

        builder.setView(view)
        selection_dialog = builder.create()
        selection_dialog!!.show()
    }

    fun hideSingleChoice() {
        if (selection_dialog != null && selection_dialog!!.isShowing)
            selection_dialog!!.dismiss()
    }

    fun hideBottomDialog() {
        if (bottom_dialog != null && bottom_dialog!!.isShowing)
            bottom_dialog!!.dismiss()
    }


    /**
     * Show Toast Message
     *
     * @param context Context
     * @param desc    String
     */
    fun showToastDialog(context: Context, desc: String) {
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show()
    }

    fun swipeRefColor(context: Context, swipe: SwipeRefreshLayout) {
        swipe.setColorSchemeColors(
            Utility.getColor(context, R.color.app_blue_dark),
            Utility.getColor(context, R.color.app_blue_light),
            Utility.getColor(context, R.color.app_gray_dark)
        )
    }

    /**
     * No Data Layout
     *
     * @param view View
     * @param show show/hide
     * @param icon Image
     * @param msg Message to show
     */

    fun showNoData(view: View, show: Boolean) {
        showNoData(view, show, null, null)
    }

    fun showNoData(view: View, show: Boolean, icon: Int?) {
        showNoData(view, show, icon, null)
    }

    fun showNoData(view: View, show: Boolean, msg: String?) {
        showNoData(view, show, null, msg)
    }

    fun showNoData(view: View, show: Boolean, icon: Int?, msg: String?) {
        try {
            val mNodataLayout = view.findViewById<LinearLayout>(R.id.layout_nodata_layout)
            val mNodataImage = view.findViewById<ImageView>(R.id.layout_nodata_image)
            val mNodataMessage = view.findViewById<TextView>(R.id.layout_nodata_message)

            if (show) {
                mNodataLayout.visibility = View.VISIBLE
                icon?.let { mNodataImage.setImageResource(it) }
                msg?.let { mNodataMessage.text = msg }
            } else mNodataLayout.visibility = View.GONE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}