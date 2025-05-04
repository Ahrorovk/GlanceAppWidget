package com.ahrorovk.myapplication.di

import android.content.Context
import androidx.room.Room
import com.ahrorovk.myapplication.core.HttpRoutes
import com.ahrorovk.myapplication.data.local.dao.HadithDao
import com.ahrorovk.myapplication.data.local.dao.PrayTimeDao
import com.ahrorovk.myapplication.data.local.dataStore.DataStoreManager
import com.ahrorovk.myapplication.data.local.db.PrayTimeDatabase
import com.ahrorovk.myapplication.data.network.HadithRepositoryImpl
import com.ahrorovk.myapplication.data.network.PrayerTimesRepositoryImpl
import com.ahrorovk.myapplication.data.network.remote.HadithApi
import com.ahrorovk.myapplication.data.network.remote.PrayerTimesApi
import com.ahrorovk.myapplication.domain.HadithRepository
import com.ahrorovk.myapplication.domain.PrayerTimesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


private const val DB_NAME = "pray_time_db"

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Singleton
    @Provides
    fun providePrayTimeApi(): PrayerTimesApi =
        Retrofit
            .Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                (OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                ).build())
            )
            .build()
            .create(PrayerTimesApi::class.java)

    @Singleton
    @Provides
    fun provideHadithApi(): HadithApi =
        Retrofit
            .Builder()
            .baseUrl(HttpRoutes.HADITH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                (OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                ).build())
            )
            .build()
            .create(HadithApi::class.java)

    @Singleton
    @Provides
    fun providePrayerTimesRepository(
        prayerTimesApi: PrayerTimesApi,
        prayTimeDao: PrayTimeDao
    ): PrayerTimesRepository =
        PrayerTimesRepositoryImpl(prayerTimesApi, prayTimeDao)

    @Singleton
    @Provides
    fun provideHadithRepository(
        hadithApi: HadithApi,
        hadithDao: HadithDao
    ): HadithRepository =
        HadithRepositoryImpl(hadithApi, hadithDao)

    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return DB_NAME//BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): PrayTimeDatabase {
        return Room.databaseBuilder(context, PrayTimeDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePrayerTimesDao(appDatabase: PrayTimeDatabase): PrayTimeDao {
        return appDatabase.prayTimeDao()
    }

    @Provides
    @Singleton
    fun provideHadithDao(appDatabase: PrayTimeDatabase): HadithDao {
        return appDatabase.hadithDao()
    }

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = DataStoreManager(context)
}