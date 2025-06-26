package com.cme.cmekotlin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cme.cmekotlin.model.Matchup
import com.cme.cmekotlin.ui.theme.InterFont

@Composable
fun MatchupHeader() {
    val header = buildAnnotatedString {
        append("Matchups ")
        withStyle(SpanStyle(color = Color(0xFF8B8B8B), fontStyle = FontStyle.Italic)) { append("(Week 8)") }
    }
    Column {
        Text(
            text = header,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            fontFamily = InterFont,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 4.dp)
        )
        Divider(
            color = Color(0xFF20FF72),
            thickness = 3.dp,
            modifier = Modifier
                .width(85.dp)
                .padding(start = 8.dp, bottom = 12.dp)
        )
    }
}

@Composable
fun MatchupTable(matchups: List<Matchup>) {
    Column(Modifier.fillMaxWidth()) {
        HeaderRow()
        Divider(color = Color(0xFF232323), thickness = 1.dp)
        matchups.forEach { matchup ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF111418)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) { MatchupCard(matchup) }
        }
    }
}

@Composable
private fun HeaderRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableHeaderCell("Proj. Pts", 1.2f)
        TableHeaderCell("", 2.3f)
        TableHeaderCell("Moneyline", 1.2f)
        TableHeaderCell("Spread", 1.1f)
        TableHeaderCell("Total", 1.2f)
    }
}

@Composable
fun RowScope.TableHeaderCell(text: String, weight: Float) {
    Text(
        text = text,
        color = Color(0xFFB5B5B5),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        fontFamily = InterFont,
        modifier = Modifier
            .weight(weight)
            .padding(vertical = 6.dp, horizontal = 2.dp)
    )
}

@Composable
fun MatchupCard(matchup: Matchup) {
    Column {
        matchup.teams.forEachIndexed { idx, team ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = team.projPts,
                    color = if (idx == 0) Color(0xFF96B4EC) else Color(0xFF20FF72),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = InterFont,
                    modifier = Modifier.weight(1.2f)
                )
                Row(
                    modifier = Modifier.weight(2.3f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = if (idx == 0) Color.White else Color(0xFFB18FFF),
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(Modifier.width(7.dp))
                    Text(
                        text = team.teamName,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        fontFamily = InterFont
                    )
                }
                MoneySpreadTotalCell(team.moneyline, Modifier.weight(1.2f))
                VerticalDivider()
                MoneySpreadTotalCell(team.spread, Modifier.weight(1.1f))
                VerticalDivider()
                MoneySpreadTotalCell(team.total, Modifier.weight(1.2f))
            }
            if (idx == 0) HorizontalDivider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Composable
private fun MoneySpreadTotalCell(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(Color(0xFF232323)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = InterFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .width(2.dp)
            .fillMaxHeight()
            .background(Color.Black)
    )
}
//End of file