package com.andreisingeleytsev.galacticaviator.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.andreisingeleytsev.galacticaviator.R
import com.andreisingeleytsev.galacticaviator.ui.navigatoins.GalacticAviatorNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        setContent {
            GalacticAviatorNavigation(mediaPlayer)
        }
    }
    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

