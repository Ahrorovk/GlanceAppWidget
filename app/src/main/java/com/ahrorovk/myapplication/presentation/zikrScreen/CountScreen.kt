package com.ahrorovk.myapplication.presentation.zikrScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.Tracing.enabled
import androidx.glance.appwidget.updateAll
import com.ahrorovk.myapplication.app.MainActivity
import com.ahrorovk.myapplication.widget.GlanceWidget
import kotlinx.coroutines.launch


@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
    var counter by remember { mutableStateOf(prefs.getInt("counter", 0)) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$counter / ${MainActivity.MAX_COUNT}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = counter.toFloat() / MainActivity.MAX_COUNT,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (counter < MainActivity.MAX_COUNT) {
                        counter++
                        prefs.edit().putInt("counter", counter).apply()
                        scope.launch {
                            val manager = GlanceAppWidgetManager(context)
                            val glanceIds = manager.getGlanceIds(GlanceWidget::class.java)
                            glanceIds.forEach { id ->
                                val glanceWidget = GlanceWidget()
                                glanceWidget.update(context, id)
                            }
                        }
                    }
                },
                enabled = counter < MainActivity.MAX_COUNT
            ) {
                Text("Increment")
            }

            Button(
                onClick = {
                    if (counter > 0) {
                        counter--
                        prefs.edit().putInt("counter", counter).apply()
                        scope.launch {
                            val manager = GlanceAppWidgetManager(context)
                            val glanceIds = manager.getGlanceIds(GlanceWidget::class.java)
                            glanceIds.forEach { id ->
                                val glanceWidget = GlanceWidget()

                                glanceWidget.update(context, id)
                            }
                        }
                    }
                },
                enabled = counter > 0
            ) {
                Text("Decrement")
            }
            Button(
                onClick = {
                    counter = 0
                    prefs.edit().putInt("counter", counter).apply()
                    scope.launch {
                        val manager = GlanceAppWidgetManager(context)
                        val glanceIds = manager.getGlanceIds(GlanceWidget::class.java)
                        glanceIds.forEach { id ->
                            val glanceWidget = GlanceWidget()
                            glanceWidget.update(context, id)
                        }
                    }
                }
            ) {
                Text("Reset")
            }
        }
    }
}