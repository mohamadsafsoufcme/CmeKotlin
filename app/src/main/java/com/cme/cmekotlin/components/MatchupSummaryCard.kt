package com.cme.cmekotlin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MatchupSummaryCard(
    homeTeamName: String,
    homeScore: Double,
    homeProj: Double,
    awayTeamName: String,
    awayScore: Double,
    awayProj: Double,
    winPercent: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF121212), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TeamScoreBlock(homeTeamName, homeScore, homeProj, Alignment.Start)
            Icon(
                imageVector = Icons.Default.Compare,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            TeamScoreBlock(awayTeamName, awayScore, awayProj, Alignment.End)
        }
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color(0xFF2AD378)
            )
            LinearProgressIndicator(
                progress = winPercent / 100f,
                modifier = Modifier
                    .weight(1f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = Color(0xFF2AD378),
                trackColor = Color.DarkGray
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFFFFC107)
            )
        }
    }
}

@Composable
private fun TeamScoreBlock(
    name: String,
    score: Double,
    proj: Double,
    align: Alignment.Horizontal
) {
    Column(horizontalAlignment = align) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = name,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = String.format("%.1f", score),
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Proj. ${String.format("%.1f", proj)}",
            color = Color.Gray,
            fontSize = 11.sp
        )
    }
}
