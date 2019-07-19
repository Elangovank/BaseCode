package com.apartment.app.activity.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.apartment.R

/**
 * Created by Mathan on 28/01/19.
 **/

class DOCSplashActivity : AppCompatActivity() {

    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mContext = this
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            try {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, 1500)
    }
}
