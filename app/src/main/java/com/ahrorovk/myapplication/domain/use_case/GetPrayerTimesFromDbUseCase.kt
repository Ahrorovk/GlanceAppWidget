package com.ahrorovk.myapplication.domain.use_case

import com.ahrorovk.myapplication.core.toMMDDYYYY
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import com.ahrorovk.myapplication.domain.PrayerTimesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPrayerTimesFromDbUseCase @Inject constructor(
    private val repository: PrayerTimesRepository
) {
    operator fun invoke(dateState: Long): Flow<List<PrayerTimesEntity>> =
        repository.getPrayTimesFromDbByDate(dateState.toMMDDYYYY())
}