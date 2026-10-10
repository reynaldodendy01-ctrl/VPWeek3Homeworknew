package com.example.vpweek3homework.soalbonus1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vpweek3homework.R
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

@Composable
fun SoalBonus1View() {
    val rupiahFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    val colorBackground = Color(0xFFFCEDE6)
    val boldTextColor = Color(0xFF3D170A)
    val normalTextColor = Color(0xFF7B482B)
    val warnaOutline = Color(0xFFF8DAB2)
    val orange = Color(0xFFD9622B)
    val snackbarHostState = remember {SnackbarHostState()}
    val scope = rememberCoroutineScope()
    var jumlah by rememberSaveable {mutableIntStateOf(3)}
    var extraLarge by rememberSaveable {mutableStateOf(false)}
    var perItem = 0
    if (extraLarge) {
        perItem = 31000
    } else {
        perItem = 25000
    }
    var subtotal = jumlah * perItem
    var tax = subtotal / 10
    var total = subtotal + tax
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(colorBackground)
                    .padding(start = 16.dp, end = 16.dp, top = 35.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                    "Kopi Kenangan Senja",
                    color = boldTextColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
            )
            Text("☕", fontSize = 20.sp)
        }
        Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = cardColors(Color.White),
                border = BorderStroke(1.dp, warnaOutline)
        ) {
            Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                        modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF6B3319)),
                        contentAlignment = Alignment.Center
                ) {
                    Icon(
                            painter = painterResource(R.drawable.baseline_coffee_24),
                            contentDescription = null,
                            tint = colorBackground,
                            modifier = Modifier.size(64.dp)
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                        "Caramel Latte",
                        color = boldTextColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                )
                Text(
                        "Espresso shot, steamed milk & caramel syrup",
                        color = normalTextColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(6.dp))
                Text(
                        "Rp 25.000 / cup",
                        color = orange,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
                "PILIHAN UKURAN: ",
                color = boldTextColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                    modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (extraLarge) colorBackground else Color.White)
                    .border(
                    width =
            if (extraLarge) {
                1.dp
            } else {
                2.dp
            },
            color =
            if (extraLarge) {
                warnaOutline
            } else {
                orange
            },
            shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                extraLarge = false
            }, contentAlignment = Alignment.Center
            ) {
                Text(
                        "Regular (+0)",
                        color =
                if (extraLarge) {
                    boldTextColor
                } else {
                    orange
                },
                fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                )

            }
            Box(
                    modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (extraLarge) Color.White else colorBackground)
                    .border(
                    width =
            if (extraLarge) {
                2.dp
            } else {
                1.dp
            },
            color =
            if (extraLarge) {
                orange
            } else {
                warnaOutline
            },
            shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                extraLarge = true
            }, contentAlignment = Alignment.Center
            ) {
                Text(
                        "Large (+6k)",
                        color =
                if (extraLarge) {
                    orange
                } else {
                    boldTextColor
                },
                fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                )
            }
        }
        Card(shape = RoundedCornerShape(12.dp), colors = cardColors(Color.White), border = BorderStroke(1.dp, Color.White)
        ) {
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Jumlah Pesanan:", color = boldTextColor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Button(
                            onClick = {
                    if (jumlah > 1) {
                        jumlah--
                    }
                        },
                    modifier = Modifier.size(36.dp),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(1.dp, Color.Gray),
                            colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFCF8F6),
                                    contentColor = boldTextColor
                            )) {
                        Text("-", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }

                    Text("$jumlah", color = boldTextColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    Button(
                            onClick = {
                                    jumlah++
                            },
                            modifier = Modifier.size(36.dp),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(1.dp, Color.Gray),
                            colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF0E5DF),
                                    contentColor = boldTextColor
                            )) {
                        Text("+", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }
            }
        }
        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .drawBehind {
            drawRoundRect(
                    color = orange.copy(alpha = 0.5f),
                    cornerRadius = CornerRadius(12.dp.toPx()),
                    style = Stroke(
                            width = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 12f))
                    )
            )
        }
                .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal:", color = boldTextColor, fontSize = 14.sp)
                Text("${rupiahFormat.format(subtotal)}", color = boldTextColor, fontSize = 14.sp)
            }
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Pajak Resto (10%):", color = boldTextColor, fontSize = 14.sp)
                Text("${rupiahFormat.format(tax)}", color = boldTextColor, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                        "Total Tagihan:", color = boldTextColor, fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                )
                Text("${rupiahFormat.format(total)}", color = orange, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.weight(1f))
        SnackbarHost(snackbarHostState)

        Button(
                onClick = {
                        var ukuran = ""
        if (extraLarge) {
            ukuran = "Large"
        } else {
            ukuran = "Regular"
        }
        scope.launch {
            snackbarHostState.showSnackbar(
                    "Anda telah menambahkan $jumlah Caramel Latte ($ukuran) ke keranjang. Total ${rupiahFormat.format(total)}"
            )
        }
            },
        modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange, contentColor = Color.White)
        ) {
            Text("TAMBAH KE KERANJANG", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SoalBonus1Preview() {
    SoalBonus1View()
}