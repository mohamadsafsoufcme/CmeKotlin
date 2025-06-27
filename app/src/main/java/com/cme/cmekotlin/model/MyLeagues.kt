package com.cme.cmekotlin.model

import java.util.Date

data class MyLeagues(
    val leagueId: String,
    val leagueName: String,
    val leagueType: LeagueType,
    val lastSynced: Date? = null,
    val syncedUsers: Int,
    val totalRosters: Int
) {
    val id: String get() = leagueId

    fun toDictionary(): Map<String, String> = mapOf(
        "leagueId" to leagueId,
        "leagueName" to leagueName,
        "leagueType" to leagueType.name,
        "syncedUsers" to syncedUsers.toString(),
        "totalRosters" to totalRosters.toString()
    )
}
enum class LeagueType {
    PUBLIC, PRIVATE,
}