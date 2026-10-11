package com.example.vpweek3homework.soal3


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
import kotlinx.coroutines.delay
import kotlin.math.ceil
import kotlin.random.Random
import kotlin.math.roundToInt

enum class halamanApa {WELCOME, HITUNG, GAME, DONE}
enum class Mode {TEXT, COLOR}
enum class Choice {TEXT, COLOR}
enum class Options(val actualWord: String, val wordColor: Color) {
    RED("RED", Color(0xFFF44336)),
    BLUE("BLUE", Color(0xFF2196F3)),
    GREEN("GREEN", Color(0xFF4CAF50)),
    ORANGE("ORANGE", Color(0xFFFF9800)),
    YELLOW("YELLOW", Color(0xFFFFEB3B)),
    PURPLE("PURPLE", Color(0xFF9C27B0))
}
@Composable

fun Soal3View(){
    var pageState by rememberSaveable {mutableStateOf(halamanApa.WELCOME)}
    var mode by rememberSaveable {mutableStateOf(Mode.COLOR)}
    var text by rememberSaveable {mutableStateOf(Options.RED)}
    var color by rememberSaveable {mutableStateOf(Options.BLUE)}
    var countDown by rememberSaveable {mutableStateOf("")}
    var counterCorrect by rememberSaveable {mutableStateOf(0)}
    var counterWrong by rememberSaveable {mutableStateOf(0)}
    var timer by rememberSaveable {mutableStateOf(5000)}
    var bestScore by rememberSaveable {mutableStateOf(0)}
    var leftButtonIsWordColor by rememberSaveable {mutableStateOf(true)}
    var questionNumber by rememberSaveable {mutableStateOf(0)}

    fun question() {
        timer = 5000
        if (Random.nextBoolean()) {
            mode = Mode.COLOR
        } else {
            mode = Mode.TEXT
        }
        text = Options.values().random()
        color = Options.values().random()
        while (color == text) {
            color = Options.values().random()
        }
        leftButtonIsWordColor = Random.nextBoolean()
        questionNumber++
    }
    fun answer(correct: Boolean) {
        if (correct) {
            counterCorrect++
        } else {
            counterWrong++
        }
        if (counterWrong == 3) {
            if (counterCorrect > bestScore) {
                bestScore = counterCorrect
            }
            pageState = halamanApa.DONE
        } else {
            question()
        }
    }

    when(pageState) {
        halamanApa.WELCOME -> {
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Text("Welcome\nto\nColor Word Matching", textAlign = TextAlign.Center, fontSize = 35.sp)
                Spacer(modifier = Modifier.height(35.dp))
                Button(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    onClick = {
                        pageState=halamanApa.HITUNG
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBCC8CF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Start Game", textAlign = TextAlign.Center, fontSize = 20.sp)
                }
            }
        }
        halamanApa.HITUNG -> {
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Text(countDown, textAlign = TextAlign.Center, fontSize = 25.sp)
            }
        }
        halamanApa.GAME -> {
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Mode: ${mode.name}", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("✅ $counterCorrect ❌ $counterWrong/3", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text("${(ceil(timer / 1000.0)).toInt()} s", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(text.actualWord, color = color.wordColor, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 60.sp)
                }
                Spacer(modifier = Modifier.height(80.dp))
                var correct = Choice.TEXT
                if (mode == Mode.COLOR) {
                    correct = Choice.COLOR
                }
                var left = Choice.TEXT
                if (leftButtonIsWordColor) {
                    left = Choice.COLOR
                }
                var right = Choice.COLOR
                if (leftButtonIsWordColor) {
                    right = Choice.TEXT
                }
                var leftLabel = text.actualWord
                if (left == Choice.COLOR) {
                    leftLabel = color.actualWord
                }

                var rightLabel = text.actualWord
                if (right == Choice.COLOR) {
                    rightLabel = color.actualWord
                }

                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        onClick = {
                            answer(left == correct)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBCC8CF),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(leftLabel, textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                    Button(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        onClick = {
                            answer(right == correct)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBCC8CF),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(rightLabel, textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                }
            }
        }
        halamanApa.DONE -> {
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))) {
                Text("Game Over!", textAlign = TextAlign.Center, fontSize = 50.sp)
                Spacer(modifier = Modifier.height(85.dp))
                Text("You're Score\n$counterCorrect", textAlign = TextAlign.Center, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(35.dp))
                Text("Best Score\n$bestScore", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(35.dp))
                Button(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    onClick = {
                        pageState=halamanApa.HITUNG
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBCC8CF),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Restart Game", textAlign = TextAlign.Center, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(35.dp))
                Button(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    onClick = {
                        pageState=halamanApa.WELCOME
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
    LaunchedEffect(pageState) {
        if (pageState == halamanApa.HITUNG) {
            var n = 3
            while (n >= 0) {
                if (n != 0) {
                    countDown = n.toString()
                } else {
                    countDown = "Start!"
                }
                if (n != 0) {
                    delay(1000)
                } else {
                    delay(700)
                }
                n--
                if (n == -1) {
                    counterCorrect = 0
                    counterWrong = 0
                    question()
                    pageState = halamanApa.GAME
                }
            }
        }
    }
    LaunchedEffect(questionNumber, pageState) {
        if (pageState == halamanApa.GAME) {
            timer = 5000
            while (timer > 0) {
                delay(100)
                timer -= 100
            }
            if (timer <= 0) {
                answer(false)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal3Preview(){
    Soal3View()
}