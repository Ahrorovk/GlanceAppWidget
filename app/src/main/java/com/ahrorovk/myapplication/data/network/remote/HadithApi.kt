package com.ahrorovk.myapplication.data.network.remote

import com.ahrorovk.myapplication.core.HttpRoutes.hadithApiKey
import com.ahrorovk.myapplication.data.model.get_hadith.HadithResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HadithApi {
    @GET("hadiths/")
    suspend fun getDailyHadith(
        @Query("apiKey") apiKey: String = hadithApiKey,
        @Query("hadithNumber") hadithNumber: Int,
        @Query("book") book: String
    ): HadithResponse
}