package com.ahrorovk.myapplication.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahrorovk.myapplication.data.local.dao.HadithDao
import com.ahrorovk.myapplication.data.local.dao.PrayTimeDao
import com.ahrorovk.myapplication.data.model.dto.HadithEntity
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity

@Database(
    entities = [PrayerTimesEntity::class,HadithEntity::class],
    version = 3,
    exportSchema = false
)
abstract class PrayTimeDatabase : RoomDatabase() {
    abstract fun prayTimeDao(): PrayTimeDao
    abstract fun hadithDao(): HadithDao
}