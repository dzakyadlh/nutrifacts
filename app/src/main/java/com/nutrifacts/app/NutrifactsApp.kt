package com.nutrifacts.app

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nutrifacts.app.data.model.UserModel
import com.nutrifacts.app.data.repository.UserRepository
import com.nutrifacts.app.ui.navigation.NavigationItem
import com.nutrifacts.app.ui.navigation.Screen
import com.nutrifacts.app.ui.screen.account.AccountScreen
import com.nutrifacts.app.ui.screen.detail.DetailScreen
import com.nutrifacts.app.ui.screen.history.HistoryScreen
import com.nutrifacts.app.ui.screen.home.HomeScreen
import com.nutrifacts.app.ui.screen.landing.LandingScreen
import com.nutrifacts.app.ui.screen.login.LoginScreen
import com.nutrifacts.app.ui.screen.notifications.NotificationsScreen
import com.nutrifacts.app.ui.screen.profile.ProfileScreen
import com.nutrifacts.app.ui.screen.saved.SavedScreen
import com.nutrifacts.app.ui.screen.scanner.ScannerActivity
import com.nutrifacts.app.ui.screen.search.SearchScreen
import com.nutrifacts.app.ui.screen.settings.SettingsScreen
import com.nutrifacts.app.ui.screen.signup.SignupScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutrifactsApp(
    modifier: Modifier = Modifier,
    userRepository: UserRepository,
    navController: NavHostController = rememberNavController()
) {

    val userSession by userRepository.getSession().collectAsState(initial = UserModel(0, "", false))
    
    val isLogin = remember {
        mutableStateOf(false)
    }

    // Observe changes in the user session and update isLogin accordingly
    LaunchedEffect(userSession) {
        isLogin.value = userSession.isLogin
    }

    Scaffold(
        topBar = {
            TopAppBar(navController)
        },
        floatingActionButton = {
            FAB(
                navController = navController)
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
                SearchScreen(navigateToDetail = { barcode ->
                    navController.navigate(
                        Screen.Detail.createRoute(barcode)
                    )
                })
            }
            composable(Screen.History.route) {
                HistoryScreen(navigateToDetail = { barcode ->
                    navController.navigate(
                        Screen.Detail.createRoute(barcode)
                    )
                })
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController)
            }
            composable(Screen.Scanner.route) {
                ScannerActivity()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("barcode") { type = NavType.StringType })
            ) {
                val barcode = it.arguments?.getString("barcode") ?: -1L
                DetailScreen(barcode = barcode.toString())
            }
            composable(Screen.Account.route) {
                AccountScreen()
            }
            composable(Screen.Saved.route) {
                SavedScreen(navigateToDetail = { barcode ->
                    navController.navigate(
                        Screen.Detail.createRoute(barcode)
                    )
                })
            }
            composable(Screen.Notifications.route) {
                NotificationsScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
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
    if (currentRoute != Screen.Landing.route && currentRoute != Screen.Login.route && currentRoute != Screen.Signup.route && currentRoute != Screen.Scanner.route) {
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
                if (currentRoute != Screen.Home.route && currentRoute != Screen.Search.route && currentRoute != Screen.History.route) {
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
                if (currentRoute == Screen.Detail.route) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                            contentDescription = stringResource(id = R.string.save)
                        )
                    }
                }
                if (currentRoute == Screen.Home.route || currentRoute == Screen.Search.route || currentRoute == Screen.History.route) {
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = stringResource(
                                R.string.menu_profile
                            )
                        )
                    }
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
private fun FAB(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    if (currentRoute == Screen.Home.route || currentRoute == Screen.Search.route || currentRoute == Screen.History.route) {
        FloatingActionButton(
            onClick = {
                val intent = Intent(context, ScannerActivity::class.java)
                context.startActivity(intent)
                Log.d("FAB", "FAB Clicked. Intent: $intent")
            },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                contentDescription = stringResource(
                    id = R.string.scanner
                )
            )
        }
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
        NavigationBar(
            modifier = modifier.border(width = 1.dp, color = MaterialTheme.colorScheme.onSurface),
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
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