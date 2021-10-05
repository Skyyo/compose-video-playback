package com.skyyo.compose_video_playback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.skyyo.compose_video_playback.manualPlayback.VideosScreen
import com.skyyo.compose_video_playback.ui.theme.ComposevideoplaybackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposevideoplaybackTheme {
                ProvideWindowInsets {
                    Surface {
                        VideosScreen()
                    }
                }
            }
        }
    }
}