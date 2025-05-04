package com.ahrorovk.myapplication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ahrorovk.myapplication.core.Cities
import com.ahrorovk.myapplication.core.getCities

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    location: String,
    expanded:Boolean,
    onExpanded: (Boolean) -> Unit,
    onClick: (Cities) -> Unit
) {
    Box(
        modifier
            .clickable {
                onExpanded(!expanded)
            }
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "location")
            Text(location)
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpanded(false) }
    ) {
        getCities().forEach { item ->
            DropdownMenuItem(
                text = { Text(item.name) },
                onClick = {
                    onClick(item)
                    onExpanded(false)
                }
            )
        }
    }
}