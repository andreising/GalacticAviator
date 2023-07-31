package com.andreisingeleytsev.galacticaviator.ui.navigatoins

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.galacticaviator.ui.screens.main_screen.MainScreen
import com.andreisingeleytsev.galacticaviator.ui.screens.rules_screen.RulesScreen
import com.andreisingeleytsev.galacticaviator.ui.screens.settings_screen.SettingsScreen
import com.andreisingeleytsev.galacticaviator.ui.screens.spaceship_screen.SpaceshipScreen
import com.andreisingeleytsev.galacticaviator.ui.screens.statistics_screen.StatisticScreen
import com.andreisingeleytsev.galacticaviator.ui.utils.Routes

@Composable
fun GalacticAviatorNavigation(mediaPlayer: MediaPlayer?) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navHostController)
        }
        composable(Routes.SETTINGS_SCREEN) {
            Log.d("tag", "check")
            SettingsScreen(navHostController = navHostController, mediaPlayer = mediaPlayer)
        }
        composable(Routes.GAME_SCREEN) {
            SpaceshipScreen(navHostController = navHostController)
        }
        composable(Routes.RULES_SCREEN) {
            RulesScreen(navHostController)
        }
        composable(Routes.STATISTICS_SCREEN) {
            StatisticScreen(navHostController)
        }
    }
}