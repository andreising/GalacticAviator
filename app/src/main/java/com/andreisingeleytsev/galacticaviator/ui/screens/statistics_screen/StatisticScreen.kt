package com.andreisingeleytsev.galacticaviator.ui.screens.statistics_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.galacticaviator.R
import com.andreisingeleytsev.galacticaviator.ui.utils.Fonts
import com.andreisingeleytsev.galacticaviator.ui.utils.Routes

@Composable
fun StatisticScreen(navHostController: NavHostController) {
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
            modifier = Modifier.padding(30.dp)
                .clickable {
                    navHostController.popBackStack()
                }
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "STATISTICS",
                style = TextStyle(
                    fontFamily = Fonts.font,
                    color = Color.White,
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.stat),
                contentDescription = null,
                modifier = Modifier.width(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
                    .clickable {
                        navHostController.popBackStack()
                    },
                contentScale = ContentScale.FillWidth
            )
        }

    }
}