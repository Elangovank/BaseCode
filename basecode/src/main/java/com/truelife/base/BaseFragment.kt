package com.truelife.base

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.truelife.storage.LocalStorageSP
import com.truelife.util.Utility

/**
 * Created by Mathan on 28/01/19.
 **/

abstract class BaseFragment : Fragment() {

    abstract fun onBackPressed()

    abstract fun onResumeFragment(): Boolean

    abstract fun init(view: View): Boolean

    abstract fun initBundle(): Boolean

    abstract fun clickListener(): Boolean

    fun checkInternet(): Boolean {
        return Utility.isInternetAvailable(this.context)
    }

    override fun onResume() {
        onResumeFragment()
        super.onResume()
    }

    fun getETValue(aEditText: EditText?): String {
        return aEditText?.text?.toString()?.trim { it <= ' ' } ?: ""
    }

    fun getTXTValue(aTextText: TextView?): String {
        return aTextText?.text?.toString()?.trim { it <= ' ' } ?: ""
    }

    fun isProvider(): Boolean {
        return LocalStorageSP[activity!!, "is_provider", false]!!
    }

}
