package com.andreisingeleytsev.galacticaviator.ui.screens.spaceship_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


sealed class SpaceshipScreenEvent {
    object MainButtonPressed: SpaceshipScreenEvent()
    object OnBack: SpaceshipScreenEvent()
    object PlayAgain: SpaceshipScreenEvent()
    data class Choose(val id: Int): SpaceshipScreenEvent()
}
