package com.ahrorovk.myapplication.data.network

import com.ahrorovk.myapplication.data.local.dao.PrayTimeDao
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import com.ahrorovk.myapplication.data.model.get_prayer_time.GetPrayerTimesResponse
import com.ahrorovk.myapplication.data.network.remote.PrayerTimesApi
import com.ahrorovk.myapplication.domain.PrayerTimesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class PrayerTimesRepositoryImpl(
    private val prayerTimesApi: PrayerTimesApi,
    private val prayTimeDao: PrayTimeDao
) : PrayerTimesRepository {

    override suspend fun getPrayerTimes(
        year: Int,
        month: Int,
        address: String,
        school: Int
    ): GetPrayerTimesResponse =
        prayerTimesApi.getPrayerTimes(year, month, address, school)

    override suspend fun insertPrayTime(prayerTimesEntity: List<PrayerTimesEntity>) =
        prayTimeDao.insert(prayerTimesEntity)

    override fun getPrayTimesFromDbByDate(date: String): Flow<List<PrayerTimesEntity>> =
        prayTimeDao.getPrayTimesFromDb(date)
}