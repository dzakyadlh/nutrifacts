package com.nutrifacts.app.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nutrifacts.app.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column {
            NewsCard()
        }
    }
}

@Composable
private fun NewsCard(modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable { }
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
//            AsyncImage(
//                model =,
//                contentDescription =,
//                contentScale = ContentScale.Crop,
//                modifier = modifier.fillMaxSize()
//            )
            Column(modifier = modifier.padding(all = 16.dp)) {
                Text(text = "Title", style = MaterialTheme.typography.titleSmall)
                Text(text = "Source", style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}