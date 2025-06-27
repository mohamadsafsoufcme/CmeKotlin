package com.cme.cmekotlin.model

data class FantasyTeam(
    val id: String,
    val name: String,
    val ownerName: String,
    val logoUrl: String,
    val fantasyPoints: Double,
    val projectedPoints: Double,
    val winProbability: Double
)
