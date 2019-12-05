package com.truelife.app.constants

import android.Manifest

/**
 * Created by Mathan on 28/01/19.
 **/

object Constant {
    internal var PREF_FILE_NAME = "DocApp_Patient"


    // App Screen Titles
    var DASHBOARD_TITLE = "Dashboard"
    var GALLERY_TITLE = "Gallery"
    var ABOUT_TITLE = "About Us"
    var CONTACT_TITLE = "Contact Us"
    var ISO_TITLE = "ISO"

    var DASHBOARD = 0
    var GALLERY = 1
    var ABOUT = 2
    var CONTACT = 3
    var ISO = 4


    // Date/Time Formats
    var DD_MM_YY = "dd-MM-yyyy" // 06-12-1993
    var YY_MM_DD = "yyyy-MM-dd" // 1993-12-06
    var YY_MM_DD_SLASH = "yyyy/MM/dd" // 1993/12/06
    var DD_MMM_YY = "dd MMM yyyy" // 06 Dec 1993
    var DD_MMM_YY_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" // 2019-03-07T16:00:00.000-05:00
    var HH_MM = "HH:mm" // 20:30
    var HH_MM_AA = "hh:mm aa" // 10:30 AM

    var CAMERA = Manifest.permission.CAMERA
    var WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    var READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    var RECORD_AUDIO = Manifest.permission.RECORD_AUDIO
    var MODIFY_AUDIO_SETTINGS = Manifest.permission.MODIFY_AUDIO_SETTINGS
}
