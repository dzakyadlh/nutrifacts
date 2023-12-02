package com.nutrifacts.app.ui.screen.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nutrifacts.app.R
import com.nutrifacts.app.ui.components.SelectionVector

@Composable
fun AccountScreen(modifier: Modifier = Modifier) {
    val username = "Admin"
    val createdAt = "25 November 2023"
    var photoUrl = null
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (photoUrl == null) {
            Image(
                imageVector = Icons.Default.AccountCircle, contentDescription = stringResource(
                    R.string.profile_pic
                ),
                modifier = modifier.size(120.dp)
            )
        } else {
            AsyncImage(
                model = photoUrl, contentDescription = stringResource(
                    R.string.profile_pic
                ),
                contentScale = ContentScale.Crop,
                modifier = modifier.size(120.dp)
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Row(modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(id = R.string.menu_profile),
                modifier = modifier
            )
            Spacer(modifier = modifier.width(8.dp))
            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.username),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(text = username, style = MaterialTheme.typography.bodyMedium)
            }
//            Icon(
//                imageVector = Icons.Default.Create,
//                contentDescription = stringResource(id = R.string.edit),
//                modifier = modifier
//            )
        }
        Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.dp)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(id = R.string.date_joined),
                modifier = modifier
            )
            Spacer(modifier = modifier.width(8.dp))
            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.date_joined),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = stringResource(id = R.string.date_joined) + " " + createdAt,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.dp)
        SelectionVector(
            icon = Icons.Default.Delete,
            label = stringResource(id = R.string.delete_account)
        )
    }
}