package com.cme.cmekotlin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cme.cmekotlin.model.LeagueInfo

@Composable
fun LeagueCard(
    name: String,
    syncedStatus: String,
    time: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth - 48.dp
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1216)),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(2.dp, Color(0xFF20FF72)),
        modifier = modifier
            .width(cardWidth)
            .height(96.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Face,
                contentDescription = null,
                tint = Color(0xFF20FFFE),
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(20.dp))
            Column {
                Text(
                    text = name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = syncedStatus,
                        color = Color(0xFFB5B5B5),
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(
                        text = "· $time",
                        color = Color(0xFFB5B5B5),
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        Icons.Default.Sync,
                        contentDescription = null,
                        tint = Color(0xFF7DFF6A),
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AddNewLeagueCard(onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth - 48.dp
    Box(
        modifier = modifier
            .width(cardWidth)
            .height(96.dp)
            .clickable(onClick = onClick)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val dash = PathEffect.dashPathEffect(floatArrayOf(12.dp.toPx(), 8.dp.toPx()))
                drawRoundRect(
                    color = Color(0xFF20FF72),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(22.dp.toPx()),
                    style = Stroke(width = strokeWidth, pathEffect = dash)
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+ Add new league",
            color = Color(0xFF20FF72),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun LeagueList(
    leagues: List<LeagueInfo>,
    onAddLeague: () -> Unit = {},
    onLeagueClick: () -> Unit = {}
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        items(leagues.size) { i ->
            val league = leagues[i]
            LeagueCard(
                name = league.name,
                syncedStatus = league.syncedStatus,
                time = league.time,
                modifier = Modifier.padding(end = 16.dp),
                onClick = onLeagueClick
            )
        }
        item {
            AddNewLeagueCard(
                onClick = onAddLeague,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}
