package com.andreisingeleytsev.galacticaviator.ui.screens.settings_screen

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.galacticaviator.R
import com.andreisingeleytsev.galacticaviator.ui.utils.Fonts

@Composable
fun SettingsScreen(navHostController: NavHostController, mediaPlayer: MediaPlayer?) {
    val sound = remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val mP = MediaPlayer.create(context, R.raw.music)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .padding(25.dp)
                .clickable {
                    navHostController.popBackStack()
                }
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "SETTINGS",
                style = TextStyle(fontFamily = Fonts.font, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(150.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SOUND",
                    style = TextStyle(fontFamily = Fonts.font, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "off",
                        style = TextStyle(fontFamily = Fonts.font, color = Color.White, fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Switch(
                        checked = sound.value, onCheckedChange = {
                            sound.value = !sound.value
                            if (sound.value) {
                                mediaPlayer!!.start()
                                mP?.start()
                                mP?.isLooping = true
                            } else {
                                mediaPlayer?.stop()
                                mP?.stop()
                            }
                        }, colors = SwitchDefaults.colors(
                            uncheckedTrackColor = Color.White,
                            checkedTrackColor = Color.Black,
                            uncheckedThumbColor = Color.Yellow,
                            checkedThumbColor = Color.Yellow
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "on",
                        style = TextStyle(fontFamily = Fonts.font, color = Color.White, fontSize = 20.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(150.dp))
            Image(
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        navHostController.popBackStack()
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}