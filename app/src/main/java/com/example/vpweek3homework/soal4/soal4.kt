package com.example.vpweek3homework.soal4

import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import com.example.vpweek3homework.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vpweek3homework.soal3.Mode
import kotlinx.coroutines.delay
import kotlin.math.ceil
import kotlin.random.Random
import kotlin.math.roundToInt

enum class RPS(val label: String, val emoji: String) {
    ROCK("Rock", "✊"), PAPER("Paper","🤚"), SCISSOR("Scissor","✌️")
}
enum class halamanApa {WELCOME, OPTION, RESULT, DONE}
enum class HASIL(val text:String) {
    WIN("You Win!"), LOSE("You Lose!"), DRAW("DRAW")
}
@Composable
fun Soal4View(){
    var n by rememberSaveable {mutableStateOf(5)}
    var pageState by rememberSaveable {mutableStateOf(halamanApa.WELCOME)}
    var playerScore by rememberSaveable {mutableStateOf(0)}
    var robotScore by rememberSaveable {mutableStateOf(0)}
    var playerChoice by rememberSaveable {mutableStateOf(RPS.ROCK)}
    var robotChoice by rememberSaveable {mutableStateOf(RPS.ROCK)}
    var result by rememberSaveable {mutableStateOf("")}
    var bestScore by rememberSaveable {mutableStateOf(0)}
    var order by rememberSaveable {mutableStateOf(RPS.values().toList().shuffled())}
    var matchWin by rememberSaveable {mutableStateOf("")}

    fun pickOption (choice: RPS) {
        playerChoice = choice
        robotChoice = RPS.values().random()

        if (playerChoice == robotChoice) {
            result = HASIL.DRAW.text
        } else if ((playerChoice == RPS.ROCK && robotChoice == RPS.SCISSOR)||(playerChoice == RPS.PAPER && robotChoice == RPS.ROCK)||(playerChoice == RPS.SCISSOR && robotChoice == RPS.PAPER)) {
            result = HASIL.WIN.text
            playerScore++
        } else {
            result = HASIL.LOSE.text
            robotScore++
        }
        pageState = halamanApa.RESULT

    }

    when (pageState) {
        halamanApa.WELCOME -> {
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Spacer(modifier = Modifier.height(250.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("🧔🏻 $playerScore - $robotScore 🤖", textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Best of $n", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("Rock·Paper·Scissors", fontSize = 30.sp)
                }
                Spacer(modifier = Modifier.height(25.dp))
                Button(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    onClick = {
                        pageState= halamanApa.OPTION
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBCC8CF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Start", textAlign = TextAlign.Center, fontSize = 20.sp)
                }
            }
        }
        halamanApa.OPTION -> {
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Spacer(modifier = Modifier.height(250.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("🧔🏻 $playerScore - $robotScore 🤖", textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Best of $n", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("Pick your move!", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("❔ VS ❔", fontSize = 50.sp)
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    order.forEach {
                            value ->
                        Button(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .semantics { contentDescription = "Pick ${value.label}" },
                            onClick = {
                                pickOption(value)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFBCC8CF),
                                contentColor = Color.Black
                            )
                        ) {
                            Text("${value.emoji} ${value.label}", textAlign = TextAlign.Center, fontSize = 12.sp)
                        }
                    }
                }

            }
        }
        halamanApa.RESULT -> {
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Spacer(modifier = Modifier.height(250.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("🧔🏻 $playerScore - $robotScore 🤖", textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Best of $n", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(85.dp))
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("${playerChoice.emoji} VS ${robotChoice.emoji}", fontSize = 60.sp)
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    Text("$result", textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .semantics { contentDescription = "Round result: ${result}" }, fontSize = 20.sp)
                }
            }
        }
        halamanApa.DONE -> {
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Spacer(modifier = Modifier.height(250.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("🧔🏻 $playerScore - $robotScore 🤖", textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Best of $n", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("$matchWin", fontSize = 30.sp)
                }
                Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("Best Score: $bestScore", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        onClick = {
                            pageState=halamanApa.OPTION
                            playerScore = 0
                            robotScore = 0
                            result = ""
                            matchWin = ""
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBCC8CF),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Restart", textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                    Button(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        onClick = {
                            pageState=halamanApa.WELCOME
                            playerScore = 0
                            robotScore = 0
                            result = ""
                            matchWin = ""
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBCC8CF),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Exit", textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                }

            }
        }
    }
    LaunchedEffect(pageState) {
        if (pageState == halamanApa.RESULT) {
            if (playerScore >= (n/2 + 1)) {
                matchWin = "You Win the Match!"
            } else if (robotScore >= (n/2 + 1)) {
                matchWin = "You Lose the Match!"
            } else {
                matchWin = ""
            }
            if (matchWin == "") {
                delay(700)
                order = RPS.values().toList().shuffled()
                pageState = halamanApa.OPTION
            } else {
                if (playerScore-robotScore >= bestScore) {
                    bestScore = playerScore-robotScore
                }
                delay(700)
                pageState = halamanApa.DONE
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal4Preview(){
    Soal4View()
}