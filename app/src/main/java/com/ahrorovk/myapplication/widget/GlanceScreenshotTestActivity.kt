package com.ahrorovk.myapplication.widget

import android.app.Activity
import android.appwidget.AppWidgetHostView
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceRemoteViews
import com.ahrorovk.myapplication.R
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
public class GlanceScreenshotTestActivity : Activity() {
    private var state: Any? = null
    private var size: DpSize = DpSize(Dp.Unspecified, Dp.Unspecified)
    private var wrapContentSize: Boolean = false
    private lateinit var hostView: AppWidgetHostView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_layout)
    }

    public fun <T> setState(state: T) {
        this.state = state
    }
    public fun setAppWidgetSize(size: DpSize) {
        this.size = size
    }

    public fun wrapContentSize() {
        this.wrapContentSize = true
    }

    @OptIn(ExperimentalGlanceRemoteViewsApi::class)
    public fun renderComposable(composable: @Composable () -> Unit) {
        runBlocking {
            val remoteViews = GlanceRemoteViews().compose(
                context = applicationContext,
                size = size,
                state = state,
                content = composable
            ).remoteViews

            val activityFrame = findViewById<FrameLayout>(R.id.content)
            hostView = TestHostView(applicationContext)
            hostView.setBackgroundColor(Color.WHITE)
            activityFrame.addView(hostView)

            val view = remoteViews.apply(applicationContext, hostView)
            hostView.addView(view)

            adjustHostViewSize()
        }
    }

    private fun adjustHostViewSize() {
        val displayMetrics = resources.displayMetrics

        if (wrapContentSize) {
            hostView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
            )
        } else {
            val hostViewPadding = Rect()
            val width =
                size.width.toPixels(displayMetrics) + hostViewPadding.left + hostViewPadding.right
            val height =
                size.height.toPixels(displayMetrics) + hostViewPadding.top + hostViewPadding.bottom

            hostView.layoutParams = FrameLayout.LayoutParams(width, height, Gravity.CENTER)
        }

        hostView.requestLayout()
    }

    private fun Dp.toPixels(displayMetrics: DisplayMetrics) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, displayMetrics).toInt()

    @RequiresApi(Build.VERSION_CODES.O)
    private class TestHostView(context: Context) : AppWidgetHostView(context) {
        init {
            setExecutor(null)
            layoutDirection = LAYOUT_DIRECTION_LOCALE
        }
    }
}