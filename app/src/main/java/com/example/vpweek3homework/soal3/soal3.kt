package com.example.vpweek3homework.soal3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

enum class GameState {
    START, COUNTDOWN, RUNNING, GAMEOVER
}
enum class Mode { COLOR, TEXT }
enum class ColorName { RED, BLUE, GREEN, ORANGE }

@Composable
fun Soal3View() {
    var currentState by remember { mutableStateOf(GameState.START) }
    var finalScore by remember { mutableStateOf(0) }
    var bestScore by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        when (currentState) {
            GameState.START -> {
                StartScreen(onStartClick = { currentState = GameState.COUNTDOWN })
            }
            GameState.COUNTDOWN -> {
                CountdownScreen(onCountdownFinished = { currentState = GameState.RUNNING })
            }
            GameState.RUNNING -> {
                RunningScreen(
                    onGameEnd = { earnedScore ->
                        finalScore = earnedScore
                        if (earnedScore > bestScore) {
                            bestScore = earnedScore
                        }
                        currentState = GameState.GAMEOVER
                    }
                )
            }
            GameState.GAMEOVER -> {
                GameOverScreen(
                    score = finalScore,
                    bestScore = bestScore,
                    onRestart = { currentState = GameState.COUNTDOWN },
                    onExit = { currentState = GameState.START }
                )
            }
        }
    }
}

@Composable
fun RunningScreen(onGameEnd: (Int) -> Unit) {
    var score by remember { mutableStateOf(0) }
    var strikes by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(5) }

    // State soal dibungkus remember agar TIDAK KE-RESET saat timeLeft berubah!
    var currentMode by remember { mutableStateOf(Mode.COLOR) }
    var wordColorName by remember { mutableStateOf(ColorName.RED) }
    var inkColorName by remember { mutableStateOf(ColorName.BLUE) }
    var wrongAnswer by remember { mutableStateOf(ColorName.BLUE) }
    var isButtonSwapped by remember { mutableStateOf(false) }

    // Fungsi untuk mengacak soal baru
    val generateNewQuestion = {
        currentMode = if (Math.random() < 0.5) Mode.COLOR else Mode.TEXT

        val allColors = ColorName.values()
        wordColorName = allColors.random()
        inkColorName = allColors.random()

        val newCorrectAnswer =
            if (currentMode == Mode.COLOR) inkColorName else wordColorName

        wrongAnswer = allColors.filter { it != newCorrectAnswer }.random()

        isButtonSwapped = Math.random() < 0.5
        timeLeft = 5
    }

    // Inisialisasi soal pertama kali saat layar masuk RUNNING
    LaunchedEffect(Unit) {
        generateNewQuestion()
    }

    // Timer terpisah: Murni hanya mengurangi timeLeft setiap 1 detik
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            if (timeLeft > 1) {
                timeLeft--
            } else {
                // Waktu habis (mencapai 1 -> 0)
                strikes++
                if (strikes >= 3) {
                    onGameEnd(score)
                    break
                } else {
                    generateNewQuestion() // Waktu habis, baru ganti soal baru
                }
            }
        }
    }

    fun getColorValue(c: ColorName): Color = when (c) {
        ColorName.RED -> Color.Red
        ColorName.BLUE -> Color.Blue
        ColorName.GREEN -> Color.Green
        ColorName.ORANGE -> Color(0xFFFFA500)
    }

    val correctAnswer = if (currentMode == Mode.COLOR) inkColorName else wordColorName


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Mode: $currentMode", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "✅ $score   ❌ $strikes/3", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        val clue = if (currentMode == Mode.COLOR) "Pilih WARNA TINTA" else "Pilih NAMA KATA"
        Text(text = clue, fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        // Tampilan Timer (Turun mulus dari 5 ke 1 tanpa mengganggu soal)
        Text(text = "$timeLeft s", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Red)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = wordColorName.name,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = getColorValue(inkColorName)
        )

        Spacer(modifier = Modifier.weight(1f))

        val btnLeftText = if (!isButtonSwapped) correctAnswer else wrongAnswer
        val btnRightText = if (!isButtonSwapped) wrongAnswer else correctAnswer

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                if (btnLeftText == correctAnswer) {
                    score++
                } else {
                    strikes++
                }

                if (strikes >= 3) {
                    onGameEnd(score)
                } else {
                    generateNewQuestion() // Tombol diklik -> Baru ganti soal baru
                }
            }) {
                Text(text = btnLeftText.name)
            }

            Button(onClick = {
                if (btnRightText == correctAnswer) {
                    score++
                } else {
                    strikes++
                }

                if (strikes >= 3) {
                    onGameEnd(score)
                } else {
                    generateNewQuestion()
                }
            }) {
                Text(text = btnRightText.name)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun GameOverScreen(score: Int, bestScore: Int, onRestart: () -> Unit, onExit: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Game Over!", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Your Score", fontSize = 18.sp, color = Color.Gray)
        Text(text = "$score", fontSize = 36.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Best Score: $bestScore", fontSize = 16.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onRestart, modifier = Modifier.width(200.dp)) {
            Text(text = "Restart Game")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onExit,
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text(text = "Exit", color = Color.Black)
        }
    }
}

@Composable
fun StartScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome", fontSize = 28.sp)
        Text(text = "to", fontSize = 24.sp, modifier = Modifier.padding(vertical = 4.dp))
        Text(text = "Color Word Matching", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCFD8DC)),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Start Game", color = Color.Black, fontSize = 18.sp)
        }
    }
}

@Composable
fun CountdownScreen(onCountdownFinished: () -> Unit) {
    var currentCount by remember { mutableStateOf(3) }
    var displayMessage by remember { mutableStateOf("3") }

    LaunchedEffect(Unit) {
        while (currentCount > 0) {
            displayMessage = currentCount.toString()
            delay(1000)
            currentCount--
        }
        displayMessage = "Start!"
        delay(700)
        onCountdownFinished()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = displayMessage,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal3Preview(){
    Soal3View()
}