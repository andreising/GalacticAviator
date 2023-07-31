package com.andreisingeleytsev.galacticaviator.ui.screens.spaceship_screen

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.galacticaviator.R
import com.andreisingeleytsev.galacticaviator.ui.utils.UIEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SpaceshipScreenViewModel: ViewModel() {
    private val _uiEvent = Channel<UIEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onEvent(event: SpaceshipScreenEvent){
        when(event){
            is SpaceshipScreenEvent.MainButtonPressed -> {
                if (!isGoing.value) {

                    sendUIEvent(UIEvents.FirstRoll)
                    start()
                    buttonText.value = "TAKE AWAY"
                    isGoing.value = true
                    balance.value=balance.value-bet.value
                } else {
                    count=0
                    timer?.cancel()
                    timer = null
                    win()
                }
            }
            is SpaceshipScreenEvent.Choose -> {
                currentSpaceShip.value = event.id
                isChoosed.value = true
                sendUIEvent(UIEvents.FirstRoll)
                sendUIEvent(UIEvents.Start)
            }
            is SpaceshipScreenEvent.PlayAgain -> {
                sendUIEvent(UIEvents.FirstRoll)
                isGoing.value = false
                isEnd.value=false
                mod.value = 0.00
                buttonText.value = "BET"
                number = (1000L..10500L).random()
            }
            is SpaceshipScreenEvent.OnBack -> {
                sendUIEvent(UIEvents.OnBack)
            }
        }
    }
    private fun sendUIEvent(event: UIEvents){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    var count = 0
    private var timer: CountDownTimer? =  null
    var number = (1000L..10000L).random()
    private fun start() {
        timer = object : CountDownTimer(number, 50) {

            override fun onTick(p0: Long) {
                mod.value+=0.03

                if (count<=15) {
                    sendUIEvent(UIEvents.Roll)
                    count++
                }
                else sendUIEvent(UIEvents.SpaceRoll)
            }

            override fun onFinish() {
                loose()
                count=0
                timer = null
            }

        }.start()
    }

    val currentSpaceShip = mutableStateOf(R.drawable.spaceship_1)
    val isChoosed = mutableStateOf(false)
    val isGoing = mutableStateOf(false)
    val buttonText = mutableStateOf("BET")
    val mod = mutableStateOf(0.00)
    val bet = mutableStateOf(10)
    val balance = mutableStateOf(240)
    val isEnd = mutableStateOf(false)
    val endGameTxt = mutableStateOf("Win!")
    private fun loose() {
        endGameTxt.value = "Loose!"
        isEnd.value = true
        sendUIEvent(UIEvents.Refresh(balance.value))
    }
    private fun win() {
        balance.value+=(bet.value*mod.value).toInt()
        endGameTxt.value = "Win!"
        isEnd.value = true
        sendUIEvent(UIEvents.Refresh(balance.value))
    }
}