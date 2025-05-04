package com.ahrorovk.myapplication.core

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Long.toMMDDYYYY(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return sdf.format(Date(this))
    /*return DateTimeFormatter.ofPattern("MM/dd/yyyy")
        .withZone(ZoneId.systemDefault())
        .format(Instant.ofEpochMilli(this))*/
}

@SuppressLint("NewApi")
fun String.toMMDDYYYY(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return LocalDate.parse(this.substring(0, 10), formatter)
}

fun Long.toMMMDD(): String {
    val sdf = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
    return sdf.format(Date(this))
    /*return DateTimeFormatter.ofPattern("MMM dd")
        .withZone(ZoneId.systemDefault())
        .format(Instant.ofEpochMilli(this))*/
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toCurrentInMillis(): Long {
    val zonedDateTime = this.atStartOfDay(ZoneId.systemDefault())
    return zonedDateTime.toInstant().toEpochMilli()
}

fun Any.showLog(tag: String = "Hello") {
    Log.d(tag, this.toString())
}
fun Long.toTimeHoursAndMinutes(): String {
    val formatter = SimpleDateFormat("HH:mm")
    return formatter.format(this)
}