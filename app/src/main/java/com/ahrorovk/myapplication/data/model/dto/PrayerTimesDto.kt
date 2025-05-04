package com.ahrorovk.myapplication.data.model.dto

data class PrayerTimesDto(
    val id: Int? = null,
    val fajrTime: String,
    val zuhrTime: String,
    val asrTime: String,
    val magribTime: String,
    val ishaTime: String,
    val islamicDate: String,
    val date: String
)
