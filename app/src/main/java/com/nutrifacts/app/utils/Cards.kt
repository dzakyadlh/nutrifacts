package com.nutrifacts.app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nutrifacts.app.R

@Composable
fun SmallCard(
    id: String,
    name: String,
    company: String,
    photoUrl: String? = null,
    navigateToDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable { navigateToDetail(id) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(start = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = company,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 8.dp,
                                bottomEnd = 8.dp,
                                bottomStart = 0.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    AsyncImage(
                        model = photoUrl ?: R.drawable.ic_launcher_background,
                        contentDescription = name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun BigCard(title:String, source:String, description:String, modifier: Modifier = Modifier, photoUrl: String?=null) {
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
            AsyncImage(
                model = photoUrl ?: R.drawable.ic_launcher_background,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth().height(150.dp)
            )
            Column(modifier = modifier.padding(all = 16.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleSmall)
                Text(text = source, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}