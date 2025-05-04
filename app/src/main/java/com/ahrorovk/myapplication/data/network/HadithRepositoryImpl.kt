package com.ahrorovk.myapplication.data.network

import com.ahrorovk.myapplication.data.local.dao.HadithDao
import com.ahrorovk.myapplication.data.model.dto.HadithEntity
import com.ahrorovk.myapplication.data.network.remote.HadithApi
import com.ahrorovk.myapplication.domain.HadithRepository

class HadithRepositoryImpl(
    val hadithApi: HadithApi,
    val hadithDao: HadithDao
) : HadithRepository {
    override suspend fun getDailyHadith(hadithNumber: Int, book: String) =
        hadithApi.getDailyHadith(hadithNumber = hadithNumber, book = book)

    override suspend fun getHadithFromDb(hadithNumber: Int): HadithEntity? =
        hadithDao.getHadithFromDb(hadithNumber)
}