package com.cme.cmekotlin.model


data class TeamStats(
    val projPts: String,
    val teamName: String,
    val moneyline: String,
    val spread: String,
    val total: String
)

data class Matchup(
    val teams: List<TeamStats>
)

data class LeagueInfo(
    val name: String,
    val syncedStatus: String,
    val time: String
)
