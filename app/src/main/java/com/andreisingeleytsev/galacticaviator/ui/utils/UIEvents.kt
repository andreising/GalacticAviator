package com.andreisingeleytsev.galacticaviator.ui.utils

sealed class UIEvents(){
    data class OnNavigate(val route: String): UIEvents()
    data class Refresh(val balance: Int): UIEvents()
    object OnBack: UIEvents()
    object Roll: UIEvents()
    object Start: UIEvents()
    object SpaceRoll: UIEvents()
    object FirstRoll: UIEvents()
}
