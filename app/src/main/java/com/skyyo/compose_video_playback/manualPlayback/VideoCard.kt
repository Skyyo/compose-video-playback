package com.skyyo.compose_video_playback.manualPlayback

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.skyyo.compose_video_playback.OnClick
import com.skyyo.compose_video_playback.R
import com.skyyo.compose_video_playback.VideoItem
import com.skyyo.compose_video_playback.ui.theme.Shapes

@Composable
fun VideoCard(
    modifier: Modifier = Modifier,
    videoItem: VideoItem,
    isPlaying: Boolean,
    exoPlayer: ExoPlayer,
    onClick: OnClick
) {
    val isPlayerUiVisible = remember { mutableStateOf(false) }
    val isPlayButtonVisible = if (isPlayerUiVisible.value) true else !isPlaying

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black, Shapes.medium)
            .clip(Shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        if (isPlaying) {
            VideoPlayer(exoPlayer) { uiVisible ->
                if (isPlayerUiVisible.value) {
                    isPlayerUiVisible.value = uiVisible
                } else {
                    isPlayerUiVisible.value = true
                }
            }
        } else {
            VideoThumbnail(videoItem.thumbnail)
        }
        if (isPlayButtonVisible) {
            Icon(
                painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(72.dp)
                    .clip(remember { RoundedCornerShape(percent = 50) })
                    .clickable { onClick() })
        }
    }
}