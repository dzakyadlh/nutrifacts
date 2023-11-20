package com.nutrifacts.app.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.nutrifacts.app.R
import com.nutrifacts.app.ui.theme.RedApple
import com.nutrifacts.app.ui.theme.YellowApple
import com.nutrifacts.app.utils.GradientButton
import com.nutrifacts.app.utils.OutlinedTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(stringResource(R.string.login), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = modifier.height(40.dp))
        OutlinedTextField(label = stringResource(R.string.email), modifier.fillMaxWidth())
        Spacer(modifier = modifier.height(24.dp))
        OutlinedTextField(label = stringResource(R.string.password), modifier.fillMaxWidth())
        Spacer(modifier = modifier.height(32.dp))
        GradientButton(
            text = stringResource(R.string.login),
            textColor = Color.Black,
            gradient = Brush.horizontalGradient(
                colors = listOf(
                    RedApple,
                    YellowApple
                ),
                startX = 25f,
            ),
            onClick = navigateToHome
        )
        Spacer(modifier = modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.new_to_nutrifacts),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = " ")
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(Color.Blue)) {
                        append(stringResource(R.string.signup))
                    }
                },
                onClick = { navigateToSignup() },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}