package com.nutrifacts.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nutrifacts.app.data.model.UserModel
import com.nutrifacts.app.ui.factory.UserViewModelFactory
import com.nutrifacts.app.ui.theme.NutrifactsTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        UserViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutrifactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user by viewModel.getSession().collectAsState(initial = UserModel(0, "", false))
                    NutrifactsApp(userIsLogin = user.isLogin)
                }
            }
        }
    }
}