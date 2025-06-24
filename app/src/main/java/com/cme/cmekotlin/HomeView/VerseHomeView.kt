package com.cme.cmekotlin.HomeView

import MatchupSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cme.cmekotlin.components.*
import com.cme.cmekotlin.model.Matchup
import com.cme.cmekotlin.model.TeamStats
import com.cme.cmekotlin.model.LeagueInfo

val sampleLeagues = listOf(
    LeagueInfo("VerseFantasyCME", "2/4 teams synced", "0 mins ago"),
    LeagueInfo("AnotherLeague", "4/4 teams synced", "3 hours ago"),
    LeagueInfo("NFL Friends", "1/4 teams synced", "10 hours ago"),
)
val sampleMatchups = listOf(
    Matchup(
        teams = listOf(
            TeamStats("112.5", "Bears", "+350", "+5.5", "44.5"),
            TeamStats("124.3", "Giants", "-475", "-5.5", "44.5")
        )
    ),
    Matchup(
        teams = listOf(
            TeamStats("110.0", "Eagles", "+120", "+3.0", "41.0"),
            TeamStats("115.8", "Cowboys", "-150", "-3.0", "41.0")
        )
    )
)

@Composable
fun LeagueHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 0.dp, vertical = 0.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        CoinHeader(balance = "3,000")
        Spacer(modifier = Modifier.height(12.dp))
        LeagueList(leagues = sampleLeagues, onAddLeague = { /* TODO: handle add */ })
        Spacer(modifier = Modifier.height(16.dp))
        MatchupSection(title = "Matchups (Week 8)", matchups = sampleMatchups)
        Spacer(modifier = Modifier.weight(1f))
        BottomNavBar()
    }
}

