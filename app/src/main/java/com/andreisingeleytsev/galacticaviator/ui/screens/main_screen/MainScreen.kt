package com.andreisingeleytsev.galacticaviator.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.galacticaviator.R
import com.andreisingeleytsev.galacticaviator.ui.utils.Routes

@Composable
fun MainScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier.padding(start = 80.dp, end = 80.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.btn_play),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            navHostController.navigate(Routes.GAME_SCREEN)
                        },
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    painter = painterResource(id = R.drawable.btn_settings),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            navHostController.navigate(Routes.SETTINGS_SCREEN)
                        },
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    painter = painterResource(id = R.drawable.btn_rules),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            navHostController.navigate(Routes.RULES_SCREEN)
                        },
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    painter = painterResource(id = R.drawable.btn_statistics),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            navHostController.navigate(Routes.STATISTICS_SCREEN )
                        },
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}