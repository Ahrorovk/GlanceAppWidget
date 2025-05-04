package com.ahrorovk.myapplication.widget

import android.R.attr.value
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.ahrorovk.myapplication.widget.presentation.prayerTimeWidget.PrayerTimeWidget
import com.ahrorovk.myapplication.widget.presentation.prayerTimeWidget.PrayerTimeWidgetReceiver
import com.google.android.glance.tools.viewer.GlanceSnapshot
import com.google.android.glance.tools.viewer.GlanceViewerActivity
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalGlanceRemoteViewsApi::class)
class MyWidgetViewerActivity : GlanceViewerActivity() {

    override suspend fun getGlanceSnapshot(
        receiver: Class<out GlanceAppWidgetReceiver>
    ): GlanceSnapshot {
        return when (receiver) {
            GlanceWidgetReceiver::class.java -> GlanceSnapshot(
                instance = GlanceWidget(),
                state = mutablePreferencesOf(intPreferencesKey("state") to value)
            )

            PrayerTimeWidgetReceiver::class.java -> GlanceSnapshot(
                instance = PrayerTimeWidget(),
                state = mutablePreferencesOf(intPreferencesKey("state") to value)
            )


            else -> throw IllegalArgumentException()
        }
    }

    override fun getProviders() =
        listOf(GlanceWidgetReceiver::class.java, PrayerTimeWidgetReceiver::class.java)
}