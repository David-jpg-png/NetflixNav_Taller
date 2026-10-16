package me.fabiansuarez.compose.lab.netflixbasicnavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.HomeScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.LoginScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep2Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep3Screen

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object RegisterStep1 : Screen("register_step1")
    data object RegisterStep2 : Screen("register_step2")
    data object RegisterStep3 : Screen("register_step3")
    data object Home : Screen("home")
}

@Composable
fun NetflixNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.RegisterStep1.route)
                }
            )
        }

        composable(Screen.RegisterStep1.route) {
            RegisterScreen(
                onNavigateToStep2 = {
                    navController.navigate(Screen.RegisterStep2.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.RegisterStep2.route) {
            RegisterStep2Screen(
                onNavigateToStep3 = {
                    navController.navigate(Screen.RegisterStep3.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.RegisterStep3.route) {
            RegisterStep3Screen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onChangePlanClick = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
