package com.truelife.app.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.truelife.R
import com.truelife.app.activity.activity.DashboardActivity
import com.truelife.app.constants.Constant
import com.truelife.base.APARTFragmentManager
import com.truelife.base.BaseFragment


/**
 * Created by Mathan on 24-09-2019.
 */

class ISOFragment : BaseFragment() {

    private var mContext: Context? = null
    private var mFragmentManager: APARTFragmentManager? = null

    private var mView: View? = null

    companion object {
        var TAG: String = ISOFragment::class.java.simpleName
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_iso, container, false)
        init(mView!!)
        return mView
    }

    override fun init(view: View): Boolean {
        mContext = activity
        mFragmentManager = APARTFragmentManager(activity!!)

        return true
    }

    override fun clickListener(): Boolean {
        return true
    }


    override fun initBundle(): Boolean {
        return true
    }


    override fun onBackPressed() {
        mFragmentManager!!.onBackPress()
    }

    override fun onResumeFragment(): Boolean {
        (mContext as DashboardActivity).setHeader(Constant.ISO_TITLE)
        return true
    }

}