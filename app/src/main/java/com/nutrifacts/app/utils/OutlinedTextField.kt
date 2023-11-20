package com.nutrifacts.app.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(label: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    androidx.compose.material3.OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(
                text = label
            )
        })
}