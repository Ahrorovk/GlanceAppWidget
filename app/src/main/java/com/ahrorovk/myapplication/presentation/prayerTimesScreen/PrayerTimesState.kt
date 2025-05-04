package com.ahrorovk.myapplication.presentation.prayerTimesScreen

import android.media.MediaPlayer
import com.ahrorovk.myapplication.core.Cities
import com.ahrorovk.myapplication.core.getCities
import com.ahrorovk.myapplication.data.model.Hadith
import com.ahrorovk.myapplication.data.model.Time
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import com.ahrorovk.myapplication.domain.state.GetPrayerTimesState

data class PrayerTimesState(
    val prayerTimesState: GetPrayerTimesState = GetPrayerTimesState(),
    val selectedCity: Cities = getCities()[0],
    val selectedMethod: Int = 1,
    val selectedSchool: Int = 1,
    val dateState: Long = 0,
    val locationState: Cities = getCities()[0],
    val expanded: Boolean = false,
    val isLoading: Boolean = false,
    val isOnline: Boolean = true,
    val prayerTimes: List<PrayerTimesEntity> = emptyList(),
    val prayerTimeByDate: PrayerTimesEntity? = null,
    val mediaPlayer: MediaPlayer? = null,
    val selectedUpcomingPrayerTimeInd: Int = 0,
    val upcomingPrayerTimes: List<Time> = emptyList(),
    val hadith: Hadith = Hadith()
)
