package com.truelife.app.activity.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import com.truelife.R
import com.truelife.app.constants.Constant.ABOUT
import com.truelife.app.constants.Constant.CONTACT
import com.truelife.app.constants.Constant.DASHBOARD
import com.truelife.app.constants.Constant.GALLERY
import com.truelife.app.constants.Constant.ISO
import com.truelife.app.fragment.*
import com.truelife.base.TLFragmentManager
import com.truelife.base.BaseActivity
import com.truelife.util.AppDialogs
import com.truelife.util.Utility
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.app_main_header.*


/**
 * Created by Elango on 28/12/19.
 **/

class DashboardActivity : BaseActivity(), BottomNavigationView.OnNavigationItemReselectedListener {

    private lateinit var mContext: Context
    private var mFragmentManager: TLFragmentManager? = null
    private var mBottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mContext = this

        init()

        clickListener()

        // Set Home Fragment
        navigateFragment(DASHBOARD)
    }


    override fun init() {
        mFragmentManager = TLFragmentManager(this)
        mBottomNavigation = findViewById(R.id.bottom_navigation)

        mBottomNavigation!!.setOnNavigationItemReselectedListener(this)
    }

    override fun clickListener() {

    }

    override fun onNavigationItemReselected(menu: MenuItem) {
        when (menu.itemId) {
            R.id.bottom_home -> navigateFragment(DASHBOARD)
            R.id.bottom_gallery -> navigateFragment(GALLERY)
            R.id.bottom_about -> navigateFragment(ABOUT)
            R.id.bottom_contact -> navigateFragment(CONTACT)
            R.id.bottom_iso -> navigateFragment(ISO)
        }

    }

    private fun navigateFragment(id: Int) {

        if (!Utility.isInternetAvailable(mContext)) {
            AppDialogs.customOkAction(
                mContext,
                null,
                getString(R.string.no_internet),
                null,
                null,
                false
            )
            return
        }

        mFragmentManager!!.clearAllFragments()

        when (id) {
            DASHBOARD -> mFragmentManager!!.replaceContent(HomeFragment(), HomeFragment.TAG, null)
            GALLERY -> mFragmentManager!!.replaceContent(GalleryFragment(), GalleryFragment.TAG, null)
            ABOUT -> mFragmentManager!!.replaceContent(AboutFragment(), AboutFragment.TAG, null)
            CONTACT -> mFragmentManager!!.replaceContent(ContactFragment(), ContactFragment.TAG, null)
            ISO -> mFragmentManager!!.replaceContent(ISOFragment(), ISOFragment.TAG, null)
        }
    }


    /**
     * @param aTitle String
     */
    fun setHeader(aTitle: String) {
        app_header_title.text = aTitle
    }
}

