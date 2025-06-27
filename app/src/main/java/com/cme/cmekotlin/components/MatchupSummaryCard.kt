package com.cme.cmekotlin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

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
    val homeWinning = homeScore >= awayScore
    val winnerColor = if (homeWinning) Color(0xFF2AD378) else Color(0xFFFFC107)
    val loserColor = if (homeWinning) Color(0xFFFFC107) else Color(0xFF2AD378)

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFF232323))
        )
        Spacer(Modifier.height(12.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = winnerColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "$winPercent%",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(winPercent / 100f)
                        .background(winnerColor)
                )
            }
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = loserColor,
                modifier = Modifier.size(24.dp)
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
