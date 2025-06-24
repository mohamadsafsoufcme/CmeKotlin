import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import com.cme.cmekotlin.model.Matchup

@Composable
fun MatchupSection(title: String, matchups: List<Matchup>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 4.dp)
        )
        Divider(
            color = Color(0xFF20FF72),
            thickness = 3.dp,
            modifier = Modifier
                .width(85.dp)
                .padding(start = 8.dp, bottom = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF18181C),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(vertical = 8.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableHeaderCell("Proj. Pts", weight = 1.2f)
                TableHeaderCell("", weight = 2.3f)
                TableHeaderCell("Moneyline", weight = 1.2f)
                TableHeaderCell("Spread", weight = 1.1f)
                TableHeaderCell("Total", weight = 1.2f)
            }
            Divider(color = Color(0xFF232323), thickness = 1.dp, modifier = Modifier.padding(bottom = 2.dp))
            matchups.forEachIndexed { index, matchup ->
                MatchupCard(matchup)
                if (index < matchups.lastIndex) {
                    Divider(
                        color = Color(0xFF18181C),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 0.dp)
                    )
                }

            }

        }
    }
}

@Composable
fun RowScope.TableHeaderCell(
    text: String,
    weight: Float,
    color: Color = Color.LightGray
) {
    Text(
        text,
        color = color,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        modifier = Modifier
            .weight(weight)
            .padding(vertical = 6.dp, horizontal = 2.dp)
    )
}

@Composable
fun MatchupCard(matchup: Matchup) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF18181C)),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column {
            matchup.teams.forEachIndexed { idx, team ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = team.projPts,
                        color = if (idx == 0) Color(0xFF96B4EC) else Color(0xFF20FF72),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1.2f)
                    )
                    Row(
                        Modifier.weight(2.3f),
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
                            fontSize = 15.sp
                        )
                    }
                    Text(
                        text = team.moneyline,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .weight(1.2f)
                            .background(Color(0xFF232323))
                            .padding(vertical = 4.dp, horizontal = 6.dp)
                    )
                    Text(
                        text = team.spread,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .weight(1.1f)
                            .background(Color(0xFF232323))
                            .padding(vertical = 4.dp, horizontal = 6.dp)
                    )
                    Text(
                        text = team.total,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .weight(1.2f)
                            .background(Color(0xFF232323))
                            .padding(vertical = 4.dp, horizontal = 6.dp)
                    )
                }
                if (idx == 0) Divider(color = Color(0xFF232323), thickness = 1.dp)
            }
        }
    }
}
