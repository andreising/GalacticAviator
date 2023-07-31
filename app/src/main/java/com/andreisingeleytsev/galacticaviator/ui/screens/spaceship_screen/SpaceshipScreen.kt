package com.andreisingeleytsev.galacticaviator.ui.screens.spaceship_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.galacticaviator.R
import com.andreisingeleytsev.galacticaviator.ui.utils.Fonts
import com.andreisingeleytsev.galacticaviator.ui.utils.UIEvents
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpaceshipScreen(navHostController: NavHostController, viewModel: SpaceshipScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current.applicationContext
    val sharedPreferences = context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

    val list = listOf(
        Pair(
            R.drawable.spaceship_1,
            "\"Pioneer 10 is a NASA space probe designed primarily to study Jupiter and the heliosphere. \"Pioneer 10 was the first spacecraft to achieve sufficient speed to overcome the Sun's gravitational force. The mission was operated by Ames Research Center in California.\n" +
                    "\n" +
                    "Launched on March 3, 1972 with the Atlas Centauri launcher. In February 1973, Pioneer 10 crossed the asteroid belt for the first time, coming closest (at 8.8 million km) to the asteroid (307) Nika and finding the dust belt closer to Jupiter. The apparatus flew 132,000 km away from Jupiter's clouds on 4 December 1973. It obtained data on the composition of Jupiter's atmosphere, clarified the planet's mass, measured its magnetic field, and found that the total heat flux from Jupiter was 2.5 times greater than the energy the planet receives from the Sun. \"Pioneer 10 also clarified the densities of Jupiter's four largest satellites."
        ),
        Pair(
            R.drawable.spaceship_2,
            "Voyager 1 is an American space probe that has been exploring the solar system since 5 September 1977. The main mission of the Voyager space programme was to explore Jupiter and Saturn. \"Voyager 1 was the first space probe to take detailed pictures of the satellites of these planets. After completing its primary mission, it embarked on an additional mission to explore remote regions of the solar system, including the Kuiper Belt and the heliospheric boundary.\u2028\n" +
                    "\"Voyager 1 is the fastest space probe to leave the solar system and the most distant man-made object from Earth. Voyager 1's current distance from Earth and the Sun, its speed and the status of its scientific instruments are displayed in real time on NASA's website. A gold plate case is attached to the spacecraft, showing the location of Earth for the suspected aliens, as well as a number of images and sounds recorded."
        ),
        Pair(
            R.drawable.spaceship_3,
            "The Cassini spacecraft was designed to explore Saturn. It has now completed the mission for which it was created, but the material it has collected will allow research to continue for about 10 more years. The Cassini spacecraft operated for 13 years and collected a lot of information about Saturn, its satellites and rings.\u2028\n" +
                    "The spacecraft arrived at its first destination in mid-2004. Since then, Saturn has had its first artificial satellite. It orbited Saturn at a speed of around 15 km/sec. The Cassini spacecraft sent the Huygens probe to Titan. The spacecraft was originally slated to operate until completion of the mission in 2008, but NASA extended it first by 2 years and then by another 7 years. During this time, several approaches to Enceladus and other satellites of Saturn have been made."
        ),
        Pair(
            R.drawable.spaceship_4,
            "\"Pioneer 10 is a NASA space probe designed primarily to study Jupiter and the heliosphere. \"Pioneer 10 was the first spacecraft to achieve sufficient speed to overcome the Sun's gravitational force. The mission was operated by Ames Research Center in California.\n" +
                    "\n" +
                    "Launched on March 3, 1972 with the Atlas Centauri launcher. In February 1973, Pioneer 10 crossed the asteroid belt for the first time, coming closest (at 8.8 million km) to the asteroid (307) Nika and finding the dust belt closer to Jupiter. The apparatus flew 132,000 km away from Jupiter's clouds on 4 December 1973. It obtained data on the composition of Jupiter's atmosphere, clarified the planet's mass, measured its magnetic field, and found that the total heat flux from Jupiter was 2.5 times greater than the energy the planet receives from the Sun. \"Pioneer 10 also clarified the densities of Jupiter's four largest satellites."
        )
    )
    val scroll = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvents.OnNavigate -> {
                    navHostController.navigate(it.route)
                }

                is UIEvents.OnBack -> {
                    navHostController.popBackStack()
                }
                is UIEvents.Roll -> {
                    scroll.scrollTo(scroll.value-100)
                }
                is UIEvents.FirstRoll -> {
                    scroll.scrollTo(scroll.maxValue)
                }
                is UIEvents.SpaceRoll -> {
                    scroll.scrollTo(scroll.value-10)
                }
                is UIEvents.Refresh -> {
                    sharedPreferences.edit()?.putInt("money", viewModel.balance.value)?.apply()
                }
                is UIEvents.Start -> {
                    val money = sharedPreferences?.getInt("money", 1000)
                    viewModel.balance.value = money!!
                }
                else -> {}
            }
        }
    }
    if (!viewModel.isChoosed.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.main_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "CHOOSE SPACESHIP",
                    style = TextStyle(
                        fontFamily = Fonts.font,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
                val state = rememberPagerState()
                val l = rememberCoroutineScope()
                HorizontalPager(count = list.size, state = state) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        l.launch {
                                            if (state.currentPage != 0) {
                                                state.scrollToPage(currentPage - 1)
                                            }
                                        }
                                    }
                            )
                            Image(
                                painter = painterResource(id = list[it].first),
                                contentDescription = null
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        l.launch {
                                            if (state.currentPage != 3) {
                                                state.scrollToPage(currentPage + 1)
                                            }
                                        }
                                    }
                            )
                        }
                        Text(
                            text = list[it].second,
                            style = TextStyle(
                                fontFamily = Fonts.font,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.choose),
                    contentDescription = null,
                    modifier = Modifier
                        .width(200.dp)
                        .clickable {
                            viewModel.onEvent(SpaceshipScreenEvent.Choose(list[state.currentPage].first))
                        },
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    } else {




        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.game_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll),
                contentScale = ContentScale.Crop
            )
        }
        if (viewModel.isEnd.value) {
            EndGame()
        } else Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier.width(130.dp), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.count),
                            contentDescription = null,
                            modifier = Modifier.width(100.dp)
                        )
                        Text(
                            text = viewModel.balance.value.toString(), modifier = Modifier.padding(start = 20.dp),
                            style = TextStyle(
                                fontFamily = Fonts.font,
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        )
                    }
                }
                Text(text = "x " + String.format("%.2f", viewModel.mod.value),
                    style = TextStyle(
                        fontFamily = Fonts.font,
                        color = Color.White,
                        fontSize = 30.sp
                    ))
                Image(
                    painter = painterResource(id = viewModel.currentSpaceShip.value),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 40.dp, end = 40.dp)
                        .height(400.dp),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = { viewModel.onEvent(SpaceshipScreenEvent.MainButtonPressed) },
                    modifier = Modifier
                        .padding(start = 40.dp, end = 40.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(
                        text = viewModel.buttonText.value,
                        style = TextStyle(
                            fontFamily = Fonts.font,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (!viewModel.isGoing.value) {
                    Card(
                        modifier = Modifier
                            .padding(start = 40.dp, end = 40.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black
                        )
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "-",
                                style = TextStyle(
                                    fontFamily = Fonts.font,
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ), modifier = Modifier
                                    .clickable {
                                        if (viewModel.bet.value != 10) {
                                            viewModel.bet.value -= 10
                                        }
                                    }
                                    .padding(5.dp))
                            Text(text = viewModel.bet.value.toString(),
                                style = TextStyle(
                                    fontFamily = Fonts.font,
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ), modifier = Modifier)
                            Text(
                                text = "+",
                                style = TextStyle(
                                    fontFamily = Fonts.font,
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ), modifier = Modifier
                                    .clickable {
                                        if (viewModel.bet.value + 10 <= viewModel.balance.value) {
                                            viewModel.bet.value += 10
                                        }
                                    }
                                    .padding(5.dp)
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun EndGame(viewModel: SpaceshipScreenViewModel = hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = viewModel.endGameTxt.value, style = TextStyle(
                fontFamily = Fonts.font,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Image(
            painter = painterResource(id = R.drawable.btn_play_again),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .clickable {
                    viewModel.onEvent(SpaceshipScreenEvent.PlayAgain)
                },
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.btn_back),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .clickable {
                    viewModel.onEvent(SpaceshipScreenEvent.OnBack)
                },
            contentScale = ContentScale.FillWidth
        )
    }
}