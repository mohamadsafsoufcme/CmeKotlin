package com.cme.cmekotlin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cme.cmekotlin.model.LeagueInfo

@Composable
fun LeagueCard(name: String, syncedStatus: String, time: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(2.dp, Color(0xFF20FF72)),
        modifier = modifier
            .width(350.dp)
            .height(88.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Face,
                contentDescription = null,
                tint = Color.Cyan,
                modifier = Modifier.size(42.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        syncedStatus,
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(
                        "· $time",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        Icons.Default.Sync,
                        contentDescription = "Synced",
                        tint = Color(0xFF7DFF6A),
                        modifier = Modifier.size(17.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AddNewLeagueCard(onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .width(260.dp)
            .height(70.dp)
            .padding(end = 12.dp)
            .clickable(onClick = onClick),
        border = BorderStroke(1.dp, Color(0xFF20FF72)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("+ Add new league", color = Color(0xFF20FF72), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LeagueList(
    leagues: List<LeagueInfo>,
    onAddLeague: () -> Unit = {}
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
                modifier = Modifier.padding(end = 12.dp)
            )
        }
        item {
            AddNewLeagueCard(onClick = onAddLeague)
        }
    }
}

//End of file
