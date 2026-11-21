package com.damanak.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damanak.app.ui.addedit.AddEditWarrantyScreen
import com.damanak.app.ui.auth.PasswordResetScreen
import com.damanak.app.ui.auth.SignInScreen
import com.damanak.app.ui.auth.SignUpScreen
import com.damanak.app.ui.care.CareScheduleScreen
import com.damanak.app.ui.components.PlaceholderScreen
import com.damanak.app.ui.detail.WarrantyDetailScreen
import com.damanak.app.ui.home.HomeScreen
import com.damanak.app.ui.onboarding.OnboardingScreen
import com.damanak.app.ui.profile.EditProfileScreen
import com.damanak.app.ui.profile.ProfileScreen
import com.damanak.app.ui.splash.SplashScreen
import com.damanak.app.ui.transfer.TransferScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    // Temporary user info until the data layer (Firebase) is connected
    var userName by rememberSaveable { mutableStateOf("اسم المستخدم") }
    var userEmail by rememberSaveable { mutableStateOf("user@example.com") }

    // After sign in / sign up: go to Home and remove the auth screens from the back stack
    val goHome: () -> Unit = {
        navController.navigate(Routes.HOME) {
            popUpTo(Routes.AUTH) { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Routes.ONBOARDING) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onFinishOnboarding = {
                    navController.navigate(Routes.AUTH) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        // Auth: Sign in (start of the auth flow)
        composable(Routes.AUTH) {
            SignInScreen(
                onSignInSuccess = goHome,
                onNavigateToSignUp = { navController.navigate(Routes.SIGN_UP) },
                onNavigateToForgotPassword = { navController.navigate(Routes.RESET_PASSWORD) }
            )
        }

        composable(Routes.SIGN_UP) {
            SignUpScreen(
                onSignUpSuccess = goHome,
                onNavigateToSignIn = { navController.popBackStack() }
            )
        }

        composable(Routes.RESET_PASSWORD) {
            PasswordResetScreen(
                onResetSent = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onAddClick = { navController.navigate(Routes.ADD_EDIT) },
                onWarrantyClick = { warranty ->
                    navController.navigate(Routes.detail(warranty.id))
                },
                onTabClick = { tab ->
                    when (tab) {
                        1 -> navController.navigate(Routes.SEARCH)
                        2 -> navController.navigate(Routes.NOTIFICATIONS)
                        3 -> navController.navigate(Routes.PROFILE)
                    }
                }
            )
        }

        composable(Routes.ADD_EDIT) {
            AddEditWarrantyScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.popBackStack() }
            )
        }

        composable(Routes.DETAIL) {
            WarrantyDetailScreen(
                onNavigateHome = { navController.popBackStack(Routes.HOME, inclusive = false) }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                name = userName,
                email = userEmail,
                onEdit = { navController.navigate(Routes.EDIT_PROFILE) },
                onReset = { navController.navigate(Routes.RESET_PASSWORD) }
            )
        }

        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(
                name = userName,
                email = userEmail,
                onBack = { navController.popBackStack() },
                onSave = { newName, newEmail ->
                    userName = newName
                    userEmail = newEmail
                    navController.popBackStack()
                }
            )
        }

        // Not built yet (planned for the next milestone)
        composable(Routes.NOTIFICATIONS) {
            PlaceholderScreen(title = Routes.NOTIFICATIONS)
        }

        composable(Routes.SEARCH) {
            PlaceholderScreen(title = Routes.SEARCH)
        }

        composable(Routes.MAINTENANCE_LOG) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            PlaceholderScreen(title = "Maintenance Log: $productId")
        }

        composable(Routes.TRANSFER) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            TransferScreen(
                productId = productId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.CARE_SCHEDULE) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            CareScheduleScreen(
                productId = productId,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }
    }
}
