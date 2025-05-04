package com.ahrorovk.myapplication.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_ASR_TIME
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_DATE
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_FAJR_TIME
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_ISHA_TIME
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_ISLAMIC_DATE
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_MAGRIB_TIME
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity.Companion.COLUMN_ZUHR_TIME
import com.ahrorovk.myapplication.data.model.get_hadith.Book
import com.ahrorovk.myapplication.data.model.get_hadith.Chapter

@Entity(
    tableName = HadithEntity.TABLE_NAME,
    indices = [Index(value = [HadithEntity.COLUMN_HADITH_NUMBER], unique = true)]
)

data class HadithEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int? = null,

    @ColumnInfo(name = COLUMN_HADITH_NUMBER)
    val hadithNumber: Int,

    @ColumnInfo(name = COLUMN_BOOK_NAME)
    val bookName: String,

    @ColumnInfo(name = COLUMN_BOOK_SLUG)
    val bookSlug: String,

    @ColumnInfo(name = COLUMN_ENGLISH_NARRATOR)
    val englishNarrator: String,

    @ColumnInfo(name = COLUMN_HADITH_ARABIC)
    val hadithArabic: String,

    @ColumnInfo(name = COLUMN_HADITH_ENGLISH)
    val hadithEnglish: String,

    @ColumnInfo(name = COLUMN_HEADING_ARABIC)
    val headingArabic: String,

    @ColumnInfo(name = COLUMN_HEADING_ENGLISH)
    val headingEnglish: String
) {
    companion object {
        const val TABLE_NAME = "hadith_table"

        const val COLUMN_ID = "id"
        const val COLUMN_HADITH_NUMBER = "hadith_number"
        const val COLUMN_BOOK_NAME = "book_name"
        const val COLUMN_BOOK_SLUG = "book_slug"
        const val COLUMN_ENGLISH_NARRATOR = "english_narrator"
        const val COLUMN_HADITH_ARABIC = "hadith_arabic"
        const val COLUMN_HADITH_ENGLISH = "hadith_english"
        const val COLUMN_HEADING_ARABIC = "heading_arabic"
        const val COLUMN_HEADING_ENGLISH = "heading_english"
    }
}
