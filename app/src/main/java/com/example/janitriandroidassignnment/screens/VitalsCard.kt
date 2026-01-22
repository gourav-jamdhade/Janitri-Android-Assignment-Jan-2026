package com.example.janitriandroidassignnment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.janitriandroidassignnment.R
import com.example.janitriandroidassignnment.data.VitalsEntry
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun VitalsCard(entry: VitalsEntry, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3C9F6)
        )
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Left-side vitals
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .padding(top = 8.dp)
                ) {
                    // Heart Rate
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart_icon),
                            contentDescription = "Heart Rate",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            "${entry.heartRate} bpm", style = TextStyle(
                                color = Color(0xFF3F0A71),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    // Weight
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.weight_icon), // replace with your icon
                            contentDescription = "Weight",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            "${entry.weight} kg", style = TextStyle(
                                color = Color(0xFF3F0A71),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }

                // Right-side vitals
                Column(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .padding(top = 8.dp)
                ) {
                    // Blood Pressure
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bp_icon), // replace with your icon
                            contentDescription = "Blood Pressure",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            "${entry.systolicBP}/${entry.diastolicBP} mmHg",
                            style = TextStyle(
                                color = Color(0xFF3F0A71),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    // Baby Kicks
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.kicks_icon), // replace with your icon
                            contentDescription = "Baby Kicks",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            "${entry.kickCount} kicks",
                            style = TextStyle(
                                color = Color(0xFF3F0A71),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF9A4CC9),
                        shape = RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp)
                    )
                    .padding(vertical = 8.dp)
                    .padding(end = 12.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = formatTimestamp(entry.timestamp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    ),
                    color = Color.White
                )
            }
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("EEE, dd MMM yyyy hh:mm a")
    return sdf.format(Date(timestamp))
}