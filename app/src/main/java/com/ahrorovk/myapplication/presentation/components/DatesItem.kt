package com.ahrorovk.myapplication.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DatesItem(
    modifier: Modifier = Modifier,
    date: String,
    islamicDate: String
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(date)
        Spacer(Modifier.padding(3.dp))
        Text(islamicDate, color = MaterialTheme.colorScheme.onSurface)
    }
}