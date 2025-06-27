package com.cme.cmekotlin.model

data class MatchupPlayer(
    val homePlayer: FantasyPlayer,
    val awayPlayer: FantasyPlayer,
    val position: String
)
