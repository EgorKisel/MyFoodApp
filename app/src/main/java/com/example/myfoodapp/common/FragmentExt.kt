package com.example.myfoodapp.common

import androidx.fragment.app.Fragment
import com.example.myfoodapp.R
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.getCurrentDate(): String {
    val simpleDateFormat = SimpleDateFormat(Const.FORMAT_DATE, Locale("ru"))
    return simpleDateFormat.format(Date())
}

fun Fragment.openDetails(fragment: Fragment, containerId: Int) {
    requireActivity().supportFragmentManager.beginTransaction()
        .add(containerId, fragment)
        .addToBackStack(null).commit()
}