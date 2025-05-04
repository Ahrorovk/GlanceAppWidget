package com.ahrorovk.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ahrorovk.myapplication.data.model.dto.HadithEntity

@Dao
interface HadithDao : BaseDao<HadithEntity> {
    @Query(
        "SELECT * FROM ${HadithEntity.TABLE_NAME} " +
                "WHERE ${HadithEntity.COLUMN_HADITH_NUMBER} = :hadithNumber"
    )
    suspend fun getHadithFromDb(hadithNumber: Int): HadithEntity?

}