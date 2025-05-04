package com.ahrorovk.myapplication.data.model.get_hadith

data class HadithResponse(
    val hadiths: Hadiths,
    val message: String,
    val status: Int
)