package com.ahrorovk.myapplication.data.model.dto

fun PrayerTimesEntity.toPrayTimeDto() = PrayerTimesDto(
    id = id,
    fajrTime = fajrTime,
    zuhrTime = zuhrTime,
    asrTime = asrTime,
    magribTime = magribTime,
    ishaTime = ishaTime,
    islamicDate = islamicDate,
    date = date
)

fun PrayerTimesDto.toPrayTimeEntity() = PrayerTimesEntity(
    id = id,
    fajrTime = fajrTime,
    zuhrTime = zuhrTime,
    asrTime = asrTime,
    magribTime = magribTime,
    ishaTime = ishaTime,
    islamicDate = islamicDate,
    date = date
)