package com.cme.cmekotlin.model
import androidx.compose.ui.text.font.FontWeight

data class FantasyPlayer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val position: String,
    val photoUrl: String,
    val teamAbbreviation: String,
    val teamId: String,
    var points: Double,
    val isDefense: Boolean,
    var finalGameOutcome: String? = null,
    var statLine: String? = null,
    var status: GameStatus? = null
) {
    val isLive: Boolean
        get() = status == GameStatus.InProgress

    fun getStatusLabel(): String = when (status) {
        GameStatus.InProgress -> "matchup.live"
        GameStatus.Final -> "matchup.final"
        GameStatus.Scheduled, null -> "matchup.projected"
    }


    fun getStatusFontWeight(): FontWeight = when (status) {
        GameStatus.InProgress -> FontWeight.Medium
        else -> FontWeight.Light
    }


    companion object {
        const val playerNotAvailableId = "N/A"

        val genericPlayer = FantasyPlayer(
            id = playerNotAvailableId,
            firstName = "N/A",
            lastName = "N/A",
            displayName = "N/A",
            position = "N/A",
            photoUrl = "N/A",
            teamAbbreviation = "N/A",
            teamId = "N/A",
            points = 0.0,
            isDefense = false,
            status = GameStatus.Scheduled
        )
    }
}
enum class GameStatus {
    Scheduled,
    InProgress,
    Final
}

