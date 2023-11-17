package com.nutrifacts.app.ui.screen.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nutrifacts.app.R

@Composable
fun HistoryScreen(modifier: Modifier = Modifier){
    Box (modifier = modifier.fillMaxSize(), Alignment.Center) {
        Text(text = stringResource(R.string.menu_history))
    }
}