package com.ahrorovk.myapplication.widget

import com.ahrorovk.myapplication.domain.PrayerTimesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WidgetEntryPoint {
    fun repo(): PrayerTimesRepository
}
