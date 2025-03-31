package com.ahrorovk.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.ahrorovk.myapplication.ui.theme.GlanceAppWidgetTheme
import com.ahrorovk.myapplication.widget.GlanceWidget
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        const val MAX_COUNT = 33
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GlanceAppWidgetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    GlanceAppWidgetTheme {
        CounterScreen()
    }
}