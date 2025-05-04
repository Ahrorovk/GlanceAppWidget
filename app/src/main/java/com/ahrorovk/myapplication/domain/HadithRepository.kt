package com.ahrorovk.myapplication.domain

import com.ahrorovk.myapplication.data.model.dto.HadithEntity
import com.ahrorovk.myapplication.data.model.get_hadith.HadithResponse

interface HadithRepository {
    suspend fun getDailyHadith(
        hadithNumber: Int,
        book: String
    ): HadithResponse

    suspend fun getHadithFromDb(hadithNumber: Int): HadithEntity?
}