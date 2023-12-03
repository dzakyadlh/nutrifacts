package com.nutrifacts.app.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.nutrifacts.app.R
import com.nutrifacts.app.ui.components.BigCard

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column {
            Text(text = buildAnnotatedString {
                append(stringResource(id = R.string.welcome_back))
                append(" ")
                append("Admin")
                append(" ")
                append("!")
            }, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.welcome_info),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(8.dp))
            BigCard(
                title = "Title",
                source = "source",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
                modifier = modifier
            )
        }
    }
}