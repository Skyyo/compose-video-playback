package com.skyyo.compose_video_playback.manualPlayback

import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.skyyo.compose_video_playback.OnClick
import com.skyyo.compose_video_playback.R
import com.skyyo.compose_video_playback.VideoItem
import com.skyyo.compose_video_playback.ui.theme.Shapes

@Composable
fun VideoCard(
    modifier: Modifier = Modifier,
    videoItem: VideoItem,
    isPlaying: Boolean,
    exoPlayer: SimpleExoPlayer,
    onClick: OnClick
) {
    val isPlayerUiVisible = remember { mutableStateOf(false) }
    val isPlayButtonVisible = if (isPlayerUiVisible.value) true else !isPlaying
    val context = LocalContext.current
    val playerView = remember {
        val layout = LayoutInflater.from(context).inflate(R.layout.video_player, null, false)
        val playerView = layout.findViewById(R.id.playerView) as PlayerView
        playerView.apply {
            setControllerVisibilityListener {
                isPlayerUiVisible.value = when {
                    isPlayerUiVisible.value -> it == View.VISIBLE
                    else -> true
                }
            }
        }
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) playerView.player = exoPlayer
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black, Shapes.medium)
            .clip(Shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        when {
            isPlaying -> {
                AndroidView(
                    modifier = Modifier
                        .height(256.dp)
                        .background(Color.Black),
                    factory = { playerView }
                )
            }
            else -> VideoThumbnail(videoItem.thumbnail)
        }
        if (isPlayButtonVisible) {
            Icon(
                painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(72.dp)
                    .clickable {
                        playerView.player = null
                        onClick()
                    })
        }
    }
}