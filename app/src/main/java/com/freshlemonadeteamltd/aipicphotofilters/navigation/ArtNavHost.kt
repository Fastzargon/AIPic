package com.freshlemonadeteamltd.aipicphotofilters.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.freshlemonadeteamltd.aipicphotofilters.screens.*
import com.freshlemonadeteamltd.aipicphotofilters.viewmodels.PhotoFilterViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

sealed class NavRoute(val route: String)
{
    object Start: NavRoute( "start_screen")
    object Main: NavRoute( "main_screen")
    object SplashScreen: NavRoute( "note_screen")
    object MainContent: NavRoute("camera_screen")
}

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalPermissionsApi
@Composable
fun ArtNavHost(photoFilterViewModel: PhotoFilterViewModel)
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.SplashScreen.route)
    {
        composable(NavRoute.Start.route)
        {
            Start(navController = navController)
        }

        composable(NavRoute.Main.route)
        {
            Main(navController = navController,photoFilterViewModel)
        }

        composable(NavRoute.SplashScreen.route)
        {
            SplashScreen(navController = navController)
        }

        composable(NavRoute.MainContent.route)
        {
            MainContent(navController = navController, Modifier.fillMaxSize())
        }


}}


