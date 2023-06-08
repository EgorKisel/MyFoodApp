package com.example.myfoodapp.commom

import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Fragment.getCurrentDate(): String {
    val simpleDateFormat = SimpleDateFormat(FORMAT_DATE, Locale(LOCALE_FORMAT_DATE))
    return simpleDateFormat.format(Date())
}

fun Fragment.openDetails(fragment: Fragment, containerId: Int) {
    requireActivity().supportFragmentManager.beginTransaction().add(containerId, fragment)
        .addToBackStack(null).commit()
}