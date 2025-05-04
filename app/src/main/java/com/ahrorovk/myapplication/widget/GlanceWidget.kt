package com.ahrorovk.myapplication.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.ahrorovk.myapplication.app.ui.theme.MyAppWidgetGlanceColorScheme
import com.ahrorovk.myapplication.widget.presentation.zikrWidget.MyContent

class GlanceWidget : GlanceAppWidget() {
    private val MAX_COUNT = 33

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val prefs = context.getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        val counter = prefs.getInt("counter", 0)

        provideContent {
            GlanceTheme(colors = MyAppWidgetGlanceColorScheme.colors) {
                MyContent(counter)
            }
        }
    }
}