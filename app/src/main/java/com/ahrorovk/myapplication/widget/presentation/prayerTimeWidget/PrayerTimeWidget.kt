package com.ahrorovk.myapplication.widget.presentation.prayerTimeWidget

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.ahrorovk.myapplication.app.MainActivity
import com.ahrorovk.myapplication.app.ui.theme.MyAppWidgetGlanceColorScheme
import com.ahrorovk.myapplication.core.Cities
import com.ahrorovk.myapplication.core.getListOfTimes
import com.ahrorovk.myapplication.core.toMMDDYYYY
import com.ahrorovk.myapplication.core.toTimeHoursAndMinutes
import com.ahrorovk.myapplication.data.model.Time
import com.ahrorovk.myapplication.data.model.dto.PrayerTimesEntity
import com.ahrorovk.myapplication.widget.WidgetEntryPoint
import dagger.hilt.android.EntryPointAccessors


class PrayerTimeWidget : GlanceAppWidget() {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val prefs = context.getSharedPreferences("city_prefs", Context.MODE_PRIVATE)
        val city = prefs.getString("city", Cities.Khujand.name)

        provideContent {
            val currentDate = System.currentTimeMillis()
            val repo = remember {
                EntryPointAccessors
                    .fromApplication(context, WidgetEntryPoint::class.java)
                    .repo()
            }
            val times by produceState(initialValue = emptyList<PrayerTimesEntity>()) {
                repo.getPrayTimesFromDbByDate(
                    currentDate.toMMDDYYYY()
                ).collect { value = it }
            }
            GlanceTheme(colors = MyAppWidgetGlanceColorScheme.colors) {
                times.forEach { time ->
                    if (time.date == currentDate.toMMDDYYYY())
                        PrayerTime(getListOfTimes(time), city ?: Cities.Khujand.name)
                }
            }
        }
    }

    @Composable
    fun PrayerTime(
        times: List<Time>,
        city: String
    ) {
        Column(
            modifier = GlanceModifier
                .background(Color(0xFF2AC2C2C2))
                .cornerRadius(12.dp)
                .padding(8.dp)
                .clickable(actionStartActivity<MainActivity>())
        ) {
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = System.currentTimeMillis().toTimeHoursAndMinutes(),
                    style = TextStyle(fontSize = 20.sp),
                    maxLines = 1
                )

                Spacer(GlanceModifier.width(6.dp))

                Text(
                    text = System.currentTimeMillis().toMMDDYYYY(),
                    style = TextStyle(fontSize = 12.sp),
                    maxLines = 1
                )

                Spacer(GlanceModifier.defaultWeight())

                Text(
                    text = city,
                    style = TextStyle(fontSize = 16.sp),
                    maxLines = 1
                )
            }

            Spacer(GlanceModifier.height(8.dp))

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                times.forEach { time ->
                    Column(
                        modifier = GlanceModifier
                            .defaultWeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = time.name,
                            style = TextStyle(fontSize = 14.sp),
                            maxLines = 1
                        )
                        Text(
                            text = time.time.substringBefore("("),
                            style = TextStyle(fontSize = 14.sp),
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}