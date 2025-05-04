package com.ahrorovk.myapplication.domain.state

import com.ahrorovk.myapplication.data.model.get_prayer_time.GetPrayerTimesResponse

data class GetPrayerTimesState(
    var isLoading: Boolean = false,
    var response: GetPrayerTimesResponse? = null,
    val error: String = ""
)
