package com.nutrifacts.app.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nutrifacts.app.R
import com.nutrifacts.app.ui.navigation.Screen
import com.nutrifacts.app.ui.components.SelectionPainter
import com.nutrifacts.app.ui.components.SelectionVector

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    var photoUrl = null
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(16.dp)) {
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
            Spacer(modifier = modifier.width(8.dp))
            Column {
                Text(text = "Admin", style = MaterialTheme.typography.titleMedium)
                Text(text = "admin@gmail.com", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.dp)
        SelectionVector(
            icon = Icons.Default.Person,
            label = stringResource(id = R.string.account),
            onClick = { navController.navigate(Screen.Account.route) }
        )
        SelectionPainter(
            icon = painterResource(id = R.drawable.baseline_bookmark_24),
            label = stringResource(id = R.string.saved_product),
            onClick = { navController.navigate(Screen.Saved.route) }
        )
        SelectionVector(
            icon = Icons.Default.Notifications,
            label = stringResource(id = R.string.notifications),
            onClick = { navController.navigate(Screen.Notifications.route) }
        )
        SelectionVector(
            icon = Icons.Default.Settings,
            label = stringResource(id = R.string.settings),
            onClick = { navController.navigate(Screen.Settings.route) }
        )
        Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.dp)
        SelectionVector(
            icon = Icons.Default.ExitToApp,
            label = stringResource(id = R.string.logout)
        )
    }
}