package com.ahrorovk.myapplication.presentation.prayerTimesScreen

import android.media.MediaPlayer
import com.ahrorovk.myapplication.core.Cities
import com.ahrorovk.myapplication.data.model.Time
import com.ahrorovk.myapplication.domain.state.GetPrayerTimesState

sealed class
PrayerTimesEvent {
    data class OnGetPrayerTimesStateChange(val state: GetPrayerTimesState) : PrayerTimesEvent()
    data class OnLocationChange(val location: Cities) : PrayerTimesEvent()
    data class OnExpandedChange(val expanded: Boolean) : PrayerTimesEvent()
    data class OnMediaPlayerChange(val mediaPlayer: MediaPlayer?) : PrayerTimesEvent()
    data class OnSelectedUpcomingPrayerTimeChange(val ind: Int) : PrayerTimesEvent()
    data class OnUpcomingPrayerTimesChange(val upcomingPrayerTimes: List<Time>) : PrayerTimesEvent()
    data class OnIsLoadingStateChange(val state: Boolean) : PrayerTimesEvent()
    data class OnIsOnlineStateChange(val isOnline: Boolean) : PrayerTimesEvent()
    object GetPrayerTimes : PrayerTimesEvent()
    object GetPrayerTimesFromDb : PrayerTimesEvent()
    data class OnDbDateChange(val dateState: Long) : PrayerTimesEvent()
}
