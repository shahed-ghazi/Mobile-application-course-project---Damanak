package com.damanak.app.ui.navigation
import com.damanak.ui.AddEditWarrantyScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damanak.app.ui.care.CareScheduleScreen
import com.damanak.app.ui.components.PlaceholderScreen
import com.damanak.app.ui.onboarding.OnboardingScreen
import com.damanak.app.ui.splash.SplashScreen
import com.damanak.app.ui.transfer.TransferScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
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

        composable(Routes.AUTH) {
            PlaceholderScreen(title = Routes.AUTH)
        }

        composable(Routes.HOME) {
            PlaceholderScreen(title = Routes.HOME)
        }

        composable(Routes.ADD_EDIT) {
            AddEditWarrantyScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.DETAIL) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            PlaceholderScreen(title = "Detail: $productId")
        }

        composable(Routes.NOTIFICATIONS) {
            PlaceholderScreen(title = Routes.NOTIFICATIONS)
        }

        composable(Routes.SEARCH) {
            PlaceholderScreen(title = Routes.SEARCH)
        }

        composable(Routes.PROFILE) {
            PlaceholderScreen(title = Routes.PROFILE)
        }

        composable(Routes.EDIT_PROFILE) {
            PlaceholderScreen(title = Routes.EDIT_PROFILE)
        }

        composable(Routes.RESET_PASSWORD) {
            PlaceholderScreen(title = Routes.RESET_PASSWORD)
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
