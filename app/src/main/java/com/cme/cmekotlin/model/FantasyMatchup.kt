//package com.cme.cmekotlin.model
//
//data class FantasyMatchup(
//    val id: String,
//    val leagueId: String,
//    val teamId: String,
//    val teamName: String,
//    val logoUrl: String,
//    val projectedFantasyPoints: Double,
//    val moneylineOdds: Int,
//    val spreadFantasyPoints: Double,
//    val matchupTotalFantasyPoints: Double,
//    val starters: List<String>,
//    val starterPositions: List<String>,
//    val startersPoints: List<PlayerPoint>,
//    val starterProjectedPoints: List<PlayerProjectedPoint>,
//    val decimalOdds: Double,
//    val ownerId: String,
//    val ownerDisplayName: String,
//    val winProbability: Double,
//    val serviceProvider: String
//) {
//    val formattedMoneyline: String
//        get() = OddsConverter.formatAmericanOdds(moneylineOdds.toDouble())
//
//    val formattedSpread: String
//        get() = OddsConverter.formatSpread(spreadFantasyPoints, moneylineOdds.toDouble() >= 0.0)
//
//    val formattedTotal: String
//        get() = OddsConverter.formatTotal(matchupTotalFantasyPoints)
//
//    val formattedPoints: String
//        get() = OddsConverter.formatFantasyPoints(projectedFantasyPoints)
//}