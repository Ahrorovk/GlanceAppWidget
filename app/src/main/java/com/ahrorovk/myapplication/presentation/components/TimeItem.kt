package com.ahrorovk.myapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ahrorovk.myapplication.R
import com.ahrorovk.myapplication.data.model.Time

@Composable
fun TimeItem(
    time: Time,
    onVolumeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (time.isTime) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.secondary
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(time.icon),
                    contentDescription = "timeIcon"
                )
                Spacer(Modifier.padding(3.dp))
                Column {
                    if (time.isTime) {
                        Text(stringResource(id = R.string.upcoming_prayer))
                        Spacer(Modifier.padding(2.dp))
                        Text(
                            "${time.name} ${stringResource(id = R.string.at)} ${
                                time.time.substring(
                                    0,
                                    5
                                )
                            }",
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(time.name)
                    }
                }
            }
            if (!time.isTime) {
                Text(time.time.substring(0, 5))
            } else {
                IconButton(
                    onClick = onVolumeClick
                ) {
                    Icon(imageVector = Icons.Default.VolumeUp, contentDescription = "volumeUp")
                }
            }
        }
    }
}