package com.example.vpweek3homework.soalbonus2

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun SoalBonus2View() {
    val snackbarHostState = remember {SnackbarHostState()}
    val scope = rememberCoroutineScope()
    val interactionSource = remember {MutableInteractionSource()}

    var wisata by rememberSaveable {mutableStateOf("Fjellllheisen Cable Car")}
    var nikmati by rememberSaveable {mutableStateOf("Pemandangan langit malam hijau aurora spektakuler dari puncak gunung...")}
    var notes by rememberSaveable {mutableStateOf("")}
    var rating by rememberSaveable {mutableStateOf("Luar Biasa ★")}
    var textStatus by rememberSaveable {mutableStateOf("Draft tidak Tersimpan")}

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111729))
            .imePadding()
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .background(
                    Brush.horizontalGradient(listOf(Color(0xFF1E3A8A), Color(0xFF3B6FD4))),
                    RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
            ) {
                Text("LOG PERJALANAN", color = Color(0xFFCFE0FF), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("Tromsø, Norway ❄", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Ekspedisi Aurora Borealis", color = Color(0xFFCFE0FF), fontSize = 12.sp)

            }
            TextField(
                value = wisata,
                onValueChange = { wisata = it },
                label = { Text("SPOT WISATA FAVORIT") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFF9AA6C4),
                    focusedContainerColor = Color(0xFF1B2238),
                    unfocusedContainerColor = Color(0xFF1B2238),
                    focusedLabelColor = Color(0xFF9AA6C4),
                    unfocusedLabelColor = Color(0xFF9AA6C4),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF4A5470), RoundedCornerShape(12.dp))
            )
            TextField(
                value = nikmati,
                onValueChange = { nikmati = it },
                label = { Text("APA YANG PALING KAMU NIKMATI?") },
                minLines = 3,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFF9AA6C4),
                    focusedContainerColor = Color(0xFF1B2238),
                    unfocusedContainerColor = Color(0xFF1B2238),
                    focusedLabelColor = Color(0xFF9AA6C4),
                    unfocusedLabelColor = Color(0xFF9AA6C4),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF4A5470), RoundedCornerShape(12.dp))
            )

            TextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("CATATAN TAMBAHAN / PERLENGKAPAN") },
                placeholder = { Text("(Ketik perlengkapan ekstra di sini...)") },
                interactionSource = interactionSource,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFF9AA6C4),
                    focusedContainerColor = Color(0xFF1B2238),
                    unfocusedContainerColor = Color(0xFF1B2238),
                    focusedPlaceholderColor = Color(0xFF9AA6C4),
                    unfocusedPlaceholderColor = Color(0xFF9AA6C4),
                    focusedLabelColor = Color(0xFF9AA6C4),
                    unfocusedLabelColor = Color(0xFF9AA6C4),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF4A5470), RoundedCornerShape(12.dp))
            )
            LaunchedEffect(Unit) {
                interactionSource.emit(FocusInteraction.Focus())
            }

            Text("TINGKAT KEPUASAN:", color = Color(0xFF9AA6C4), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Biasa", "Seru", "Luar Biasa ★").forEach {choice ->
                    val picked = rating == choice
                    Button(
                        onClick = { rating = choice },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (picked) {Color(0xFF62BAF3)}
                                else {Color(0xFF111729)},
                            contentColor =
                                if (picked) {Color.Black}
                                else {Color(0xFF9AA6C4)}
                        ),
                        border = if (picked) {null} else {BorderStroke(1.dp, Color(0xFF9AA6C4))}
                    ) {
                        Text(choice, fontSize = 12.sp, fontWeight = if (picked) {FontWeight.Bold} else {FontWeight.Normal})
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row (modifier = Modifier.padding(bottom = 20.dp)){
                Text("Status: ", color = Color(0xFF9AA6C4), fontSize = 12.sp)
                Text("$textStatus", color = Color(0xFF62BAF3), fontSize = 12.sp, fontWeight = FontWeight.Bold
                )
            }


        }
        FloatingActionButton(
            onClick = {
                val teksTampil = "Jurnal tersimpan! Spot favorit: ${wisata.ifBlank { "(belum diisi)" }}"
                scope.launch {
                    snackbarHostState.showSnackbar(teksTampil)
                }
                textStatus = "Draft Tersimpan"
            },
            containerColor = Color(0xFF62BAF3),
            contentColor = Color(0xFF111729),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 20.dp)
        ) {
            Text("+", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 88.dp)
        )
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SoalBonus2Preview() {
    SoalBonus2View()
}