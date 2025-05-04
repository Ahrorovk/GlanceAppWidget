package com.ahrorovk.myapplication.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = PrayerTimesEntity.TABLE_NAME,
    indices = [Index(value = [PrayerTimesEntity.COLUMN_DATE], unique = true)])

data class PrayerTimesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Int? = null,
    @ColumnInfo(name = COLUMN_FAJR_TIME) val fajrTime: String,
    @ColumnInfo(name = COLUMN_ZUHR_TIME) val zuhrTime: String,
    @ColumnInfo(name = COLUMN_ASR_TIME) val asrTime: String,
    @ColumnInfo(name = COLUMN_MAGRIB_TIME) val magribTime: String,
    @ColumnInfo(name = COLUMN_ISHA_TIME) val ishaTime: String,
    @ColumnInfo(name = COLUMN_ISLAMIC_DATE) val islamicDate: String,
    @ColumnInfo(name = COLUMN_DATE) val date: String,
) {
    companion object {
        const val TABLE_NAME = "pray_time_table"
        const val COLUMN_ID = "id"
        const val COLUMN_FAJR_TIME = "fajr_time"
        const val COLUMN_ZUHR_TIME = "zuhr_time"
        const val COLUMN_ASR_TIME = "asr_time"
        const val COLUMN_MAGRIB_TIME = "magrib_time"
        const val COLUMN_ISHA_TIME = "isha_time"
        const val COLUMN_ISLAMIC_DATE = "islamic_date"
        const val COLUMN_DATE = "date"
    }
}
