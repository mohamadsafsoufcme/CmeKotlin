package com.example.fantasy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.cme.cmekotlin.components.*

data class PositionMatchup(
    val position: String,
    val left: PlayerProjection,
    val right: PlayerProjection
)

@Composable
fun LeagueMatchupView(
    matchups: List<PositionMatchup>,
    modifier: Modifier = Modifier,
    onPlaceEntry: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(Modifier.fillMaxSize()) {
            CoinHeader(balance = "5,000", onBack = {})
            Spacer(Modifier.height(12.dp))
            MatchupSummaryCard(
                homeTeamName = "JackGorelick's Team",
                homeScore = 110.0,
                homeProj = 180.0,
                awayTeamName = "davidwrosen's Team",
                awayScore = 98.0,
                awayProj = 118.4,
                winPercent = 62
            )
            Spacer(Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(matchups) { m ->
                    MatchupRow(
                        left = m.left,
                        right = m.right,
                        positionLabel = m.position
                    )
                }
            }
            BottomNavBar(Modifier.fillMaxWidth())
        }
        PlaceEntryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 86.dp),
            onClick = onPlaceEntry
        )
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun LeagueMatchupPreview() {
    val sample = listOf(
        PositionMatchup(
            "QB",
            PlayerProjection("Lamar Jackson", "BAL", 20.61),
            PlayerProjection("Jalen Hurts", "PHI", 18.59)
        ),
        PositionMatchup(
            "RB",
            PlayerProjection("Jonathan Taylor", "IND", 14.80),
            PlayerProjection("Travis Etienne Jr.", "JAX", 0.00)
        ),
        PositionMatchup(
            "RB",
            PlayerProjection("James Conner", "ARI", 12.20),
            PlayerProjection("Derrick Henry", "BAL", 16.38)
        ),
        PositionMatchup(
            "WR",
            PlayerProjection("CeeDee Lamb", "DAL", 16.85),
            PlayerProjection("Mike Evans", "TB", 0.00)
        ),
        PositionMatchup(
            "WR",
            PlayerProjection("Marvin Harrison Jr.", "ARI", 12.90),
            PlayerProjection("Ja'Marr Chase", "CIN", 17.56)
        ),
        PositionMatchup(
            "TE",
            PlayerProjection("Kyle Pitts", "ATL", 8.70),
            PlayerProjection("Mark Andrews", "BAL", 7.05)
        )
    )
    LeagueMatchupView(sample)
}
