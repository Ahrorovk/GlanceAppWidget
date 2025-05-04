package com.ahrorovk.myapplication.data.network.remote

import com.ahrorovk.myapplication.data.model.get_prayer_time.GetPrayerTimesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PrayerTimesApi {
    @GET("calendarByAddress/{year}/{month}")
    suspend fun getPrayerTimes(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Query("address") address: String,
        @Query("school") school: Int
    ): GetPrayerTimesResponse
}