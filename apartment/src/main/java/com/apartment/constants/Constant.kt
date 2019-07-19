package com.apartment.constants

import android.Manifest

/**
 * Created by Mathan on 28/01/19.
 **/

object Constant {
    internal var PREF_FILE_NAME = "DocApp_Patient"


    // App Screen Titles
    var DASHBOARD_TITLE = "Dashboard"
    var MY_HISTORY_TITLE = "My History"
    var PATIENT_RECORDS = "Patient Records"
    var YOUR_PROFILE_TITLE = "Your Profile"
    var BOOK_APPOINTMENT_TITLE = "Book Appointment"
    var PATIENT_INFO = "Patient Information"
    var APPOINTMENT_TITLE = "Appointments"

    var DASHBOARD = 0
    var MY_HISTORY = 1
    var SETTINGS = 2
    var LOGOUT = 3
    var PROVIDER_LOGOUT = 4

    var OPTION_CALL = 0
    var OPTION_INFO = 1
    var OPTION_HISTORY = 2
    var OPTION_RESCHEDULE = 3
    var OPTION_CANCEL = 4

    var PLAN_ONE = 1
    var PLAN_TWO = 2
    var PLAN_THREE = 3
    var PLAN_FOUR = 4

    var GET_EMAIL = 1

    var GET_TERMS = 1
    var ACCEPT_TERMS = 2

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
