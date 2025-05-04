package com.ahrorovk.myapplication.data.model.get_prayer_time

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)