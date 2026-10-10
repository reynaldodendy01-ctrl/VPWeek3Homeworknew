package com.example.vpweek3homework.soal2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vpweek3homework.R

class soal2 {



    fun formatNumber(value: Double): String {
        return value.toInt().toString()
    }

    @Composable
    fun Soal2View(){
        val catInteractionSource = remember { MutableInteractionSource() }
        val isCatPressed by catInteractionSource.collectIsPressedAsState()
        val catImage = if (isCatPressed) {
            R.drawable.catmiaw
        } else {
            R.drawable.catsilent
        }
        val catText = if (isCatPressed) {
            "Meow!"
        } else {
            "Purr~"
        }
        var currentCoins by remember { mutableStateOf(0.0) }
        var coinPerTap by remember { mutableStateOf(1.0) }

        var upgradeCost by remember { mutableStateOf(10.0) }
        var uangCukup = currentCoins >= upgradeCost
        var tambahan = (coinPerTap * 1.5)



        Box() {
            Image(
                painter = painterResource(R.drawable.bgsoal2),
                "",
                Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = Color.Black.copy(alpha = 0.4f),
                    blendMode = BlendMode.SrcOver
                )
            )
            Column() {
                Row(
                    Modifier.padding(40.dp).padding(top = 30.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.3f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Your Coins",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )

                            Text(
                                text = formatNumber(currentCoins),
                                Modifier.padding(5.dp),
                                color = Color(0xFF00FF88),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,

                                )

                            Text(
                                text = "${formatNumber(coinPerTap)} coins per tap",
                                color = Color.White, fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Tap the cat",
                            Modifier.padding(12.dp),
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = painterResource(catImage),
                            contentDescription = "Tap the Cat",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(180.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                                .clickable(
                                    interactionSource = catInteractionSource,
                                    indication = null,
                                    onClick = {
                                        currentCoins += coinPerTap
                                    }

                                )
                        )

                        Text(
                            catText,
                            Modifier.padding(12.dp),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
                Row(
                    Modifier.padding(40.dp).padding(top = 10.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .width(400.dp)
                            .height(170.dp),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Give me your coin",
                                Modifier.padding(5.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                            )

                            Text(
                                text = "Next upgrade: +${formatNumber(tambahan)} coins per tap",
                                fontSize = 16.sp
                            )

                            Button(
                                onClick = {
                                    if (uangCukup) {
                                        currentCoins = currentCoins - upgradeCost
                                        upgradeCost = upgradeCost * 2
                                        coinPerTap = coinPerTap * 1.5

                                    }
                                },
                                Modifier.width(320.dp).height(60.dp).padding(5.dp),
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                                enabled = uangCukup,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4CAF50),
                                    disabledContainerColor = Color.LightGray
                                )
                            ) {
                                if (uangCukup) {
                                    Text(
                                        "Pay for ${formatNumber(upgradeCost)} coins",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                } else {
                                    val kurang = upgradeCost - currentCoins
                                    Text(
                                        "Find ${formatNumber(kurang)} more coins",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun Soal2Preview(){
        Soal2View()
    }

}