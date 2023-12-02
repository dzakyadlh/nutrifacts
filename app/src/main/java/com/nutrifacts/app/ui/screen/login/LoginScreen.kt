package com.nutrifacts.app.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nutrifacts.app.R
import com.nutrifacts.app.ui.components.GradientButton
import com.nutrifacts.app.ui.theme.RedApple
import com.nutrifacts.app.ui.theme.YellowApple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    //    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
//        factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current))
//    ),
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    val viewModel = viewModel<LoginViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.success -> {
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                    navigateToHome()
                }
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(stringResource(R.string.login), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = modifier.height(40.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginFormEvent.EmailChanged(it)) },
            label = { Text(text = stringResource(id = R.string.email)) },
            isError = state.emailError != null,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error,
                modifier = modifier.align(Alignment.End),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = modifier.height(10.dp))
        } else {
            Spacer(modifier = modifier.height(24.dp))
        }
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) },
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = stringResource(id = R.string.password)) }
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error,
                modifier = modifier.align(Alignment.End),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = modifier.height(10.dp))
        } else {
            Spacer(modifier = modifier.height(24.dp))
        }
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
            onClick = { viewModel.onEvent(LoginFormEvent.Submit) }
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