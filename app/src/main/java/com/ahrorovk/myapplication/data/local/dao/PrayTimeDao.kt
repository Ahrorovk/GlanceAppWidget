package com.ahrorovk.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayTimeDao : BaseDao<PrayerTimesEntity> {
    @Query("SELECT * FROM ${PrayerTimesEntity.TABLE_NAME} WHERE date=:date")
    fun getPrayTimesFromDb(date:String): Flow<List<PrayerTimesEntity>>
}