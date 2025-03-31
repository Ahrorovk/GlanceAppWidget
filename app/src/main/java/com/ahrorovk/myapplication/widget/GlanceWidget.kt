package com.ahrorovk.myapplication.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.ahrorovk.myapplication.MainActivity
import com.ahrorovk.myapplication.R
import com.ahrorovk.myapplication.ui.theme.MyAppWidgetGlanceColorScheme

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

    @Composable
    private fun MyContent(counter: Int) {

        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background).padding(2.dp)
                .clickable(actionStartActivity<MainActivity>()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Зикр",
                    style = TextStyle(
                        textAlign = TextAlign.Start
                    ),
                )
                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Image(
                        modifier = GlanceModifier.size(35.dp),
                        provider = ImageProvider(resId = R.drawable.sajda),
                        contentDescription = "sajda app"
                    )
                }
            }

            Box(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
            ) {
                LinearProgressIndicator(
                    progress = counter.toFloat() / MainActivity.MAX_COUNT,
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .height(28.dp)
                        .padding(horizontal = 2.dp)
                )

                Text(
                    text = "$counter / $MAX_COUNT",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    ),
                    modifier = GlanceModifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }

        }
    }
}