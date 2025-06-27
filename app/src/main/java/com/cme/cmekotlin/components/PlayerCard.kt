package com.example.fantasy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PlayerProjection(
    val name: String,
    val team: String,
    val projection: Double
)

@Composable
fun MatchupRow(
    left: PlayerProjection,
    right: PlayerProjection,
    positionLabel: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF111214))
        ) {
            PlayerSide(player = left, isLeft = true, modifier = Modifier.weight(1f))
            PositionDivider(label = positionLabel)
            PlayerSide(player = right, isLeft = false, modifier = Modifier.weight(1f))
        }
        HorizontalDivider(thickness = 1.dp, color = Color.Black.copy(alpha = 0.75f))
    }
}

@Composable
private fun PlayerSide(
    player: PlayerProjection,
    isLeft: Boolean,
    modifier: Modifier
) {
    Box(modifier = modifier.fillMaxHeight()) {
        val projAlign = if (isLeft) Alignment.TopEnd else Alignment.TopStart
        Column(
            modifier = Modifier
                .align(projAlign)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = String.format("%.2f", player.projection),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Proj",
                fontSize = 8.sp,
                color = Color(0xFF9C9C9C)
            )
        }
        val infoAlign = if (isLeft) Alignment.BottomStart else Alignment.BottomEnd
        Row(
            modifier = Modifier
                .align(infoAlign)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLeft) {
                AvatarPlaceholder()
                Spacer(Modifier.width(4.dp))
                NameTeam(player, TextAlign.Start)
            } else {
                NameTeam(player, TextAlign.End)
                Spacer(Modifier.width(4.dp))
                AvatarPlaceholder()
            }
        }
    }
}

@Composable
private fun AvatarPlaceholder() {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFF505050))
    )
}

@Composable
private fun NameTeam(player: PlayerProjection, textAlign: TextAlign) {
    Column {
        Text(
            text = player.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            textAlign = textAlign,
            maxLines = 1
        )
        Text(
            text = player.team,
            fontSize = 10.sp,
            color = Color(0xFF9C9C9C),
            textAlign = textAlign
        )
    }
}

@Composable
private fun PositionDivider(label: String) {
    Column(
        modifier = Modifier
            .width(52.dp)
            .fillMaxHeight()
            .background(Color(0xFF23282B)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9C9C9C)
        )
    }
}
