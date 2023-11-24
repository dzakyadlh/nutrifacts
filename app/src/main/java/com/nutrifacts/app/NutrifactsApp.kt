package com.nutrifacts.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nutrifacts.app.ui.navigation.NavigationItem
import com.nutrifacts.app.ui.navigation.Screen
import com.nutrifacts.app.ui.screen.history.HistoryScreen
import com.nutrifacts.app.ui.screen.home.HomeScreen
import com.nutrifacts.app.ui.screen.landing.LandingScreen
import com.nutrifacts.app.ui.screen.login.LoginScreen
import com.nutrifacts.app.ui.screen.profile.ProfileScreen
import com.nutrifacts.app.ui.screen.search.SearchScreen
import com.nutrifacts.app.ui.screen.signup.SignupScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutrifactsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val isLogin = remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(navController)
        },
        bottomBar = {
            BottomAppBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isLogin.value) Screen.Home.route else Screen.Landing.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Landing.route) {
                LandingScreen(navigateToLogin = {
                    navController.navigate(
                        Screen.Login.route
                    )
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToSignup = { navController.navigate(Screen.Signup.route) },
                    navigateToHome = { navController.navigate(Screen.Home.route) })
            }
            composable(Screen.Signup.route) {
                SignupScreen(
                    navigateToLogin = { navController.navigate(Screen.Login.route) })
            }
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute != Screen.Landing.route && currentRoute != Screen.Login.route && currentRoute != Screen.Signup.route) {
        androidx.compose.material3.TopAppBar(
            title = {
                Text(
                    text = currentRoute.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            navigationIcon = {
                if (currentRoute == Screen.Profile.route || currentRoute == Screen.Settings.route) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                R.string.menu_back
                            )
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(
                            R.string.menu_profile
                        )
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(R.string.menu_settings)
                    )

                }
            },
            scrollBehavior = scrollBehavior,
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .shadow(1.dp)
        )
    }
}

@Composable
private fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == Screen.Home.route || currentRoute == Screen.Search.route || currentRoute == Screen.History.route) {
        NavigationBar(modifier = modifier) {
            val navigationItems = listOf(
                NavigationItem(
                    title = stringResource(R.string.menu_home),
                    icon = Icons.Default.Home,
                    screen = Screen.Home
                ),
                NavigationItem(
                    title = stringResource(R.string.menu_search),
                    icon = Icons.Default.Search,
                    screen = Screen.Search
                ),
                NavigationItem(
                    title = stringResource(R.string.menu_history),
                    icon = Icons.Default.List,
                    screen = Screen.History
                )
            )
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}