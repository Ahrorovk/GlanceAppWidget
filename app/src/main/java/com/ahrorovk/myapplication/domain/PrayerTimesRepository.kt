package com.ahrorovk.myapplication.domain

import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import com.ahrorovk.myapplication.data.model.get_prayer_time.GetPrayerTimesResponse
import kotlinx.coroutines.flow.Flow

interface PrayerTimesRepository {

    suspend fun getPrayerTimes(
        year: Int,
        month: Int,
        address: String,
        school: Int
    ): GetPrayerTimesResponse


    suspend fun insertPrayTime(prayerTimesEntity: List<PrayerTimesEntity>)

    fun getPrayTimesFromDbByDate(date:String): Flow<List<PrayerTimesEntity>>
}