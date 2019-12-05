package com.truelife

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.truelife.app.activity.activity.DashboardActivity
import com.truelife.app.constants.Constant
import com.truelife.base.TLFragmentManager
import com.truelife.base.BaseFragment


/**
 * Created by Mathan on 24-09-2019.
 */

class DummyFragment : BaseFragment() {

    private var mContext: Context? = null
    private var mFragmentManager: TLFragmentManager? = null

    private var mView: View? = null

    companion object {
        var TAG: String = DummyFragment::class.java.simpleName
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.activity_dashboard, container, false)
        init(mView!!)
        return mView
    }

    override fun init(view: View): Boolean {
        mContext = activity
        mFragmentManager = TLFragmentManager(activity!!)

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
        (mContext as DashboardActivity).setHeader(Constant.DASHBOARD_TITLE)
        return true
    }

}