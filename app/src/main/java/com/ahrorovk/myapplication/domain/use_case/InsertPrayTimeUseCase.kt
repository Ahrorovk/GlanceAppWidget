package com.ahrorovk.myapplication.domain.use_case

import com.ahrorovk.myapplication.data.model.dto.PrayerTimesDto
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import com.ahrorovk.myapplication.data.model.dto.toPrayTimeEntity
import com.ahrorovk.myapplication.domain.PrayerTimesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class InsertPrayTimeUseCase @Inject constructor(
    private val repository: PrayerTimesRepository
) {
    val prayerTimes = mutableListOf<PrayerTimesEntity>()
    suspend operator fun invoke(prayerTimesDto: List<PrayerTimesDto>) {
        prayerTimesDto.forEach {
            prayerTimes.add(it.toPrayTimeEntity())
        }
        delay(100)
        repository.insertPrayTime(prayerTimes)
    }
}