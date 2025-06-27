package com.cme.cmekotlin.homeview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cme.cmekotlin.components.*
import com.cme.cmekotlin.model.LeagueInfo
import com.cme.cmekotlin.model.Matchup
import com.cme.cmekotlin.model.TeamStats

private val sampleLeagues = listOf(
    LeagueInfo("VerseFantasyCME", "2/4 teams synced", "0 mins ago")
)

private val sampleMatchups = listOf(
    Matchup(
        teams = listOf(
            TeamStats("123.7", "JoeDroid's Team", "+104", "+1.1", "O 248.5"),
            TeamStats("124.8", "danzimm's Team", "-110", "-1.1", "U 248.5")
        )
    )
)

@Composable
fun LeagueHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF14191F),
                            Color(0xFF0F141A),
                            Color(0xFF0A0E12)
                        )
                    )
                )
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
                CoinHeader(balance = "3,000")
                Spacer(Modifier.height(16.dp))
                LeagueList(leagues = sampleLeagues)
                Spacer(Modifier.height(4.dp))
                MatchupHeader()
            }
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            MatchupTable(matchups = sampleMatchups)
        }

        Spacer(Modifier.weight(1f))
        BottomNavBar()
    }
}
