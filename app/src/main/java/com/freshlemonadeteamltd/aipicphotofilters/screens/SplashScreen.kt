package com.freshlemonadeteamltd.aipicphotofilters.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.freshlemonadeteamltd.aipicphotofilters.R
import com.freshlemonadeteamltd.aipicphotofilters.navigation.NavRoute
import com.freshlemonadeteamltd.aipicphotofilters.ui.theme.AIPicPhotoFiltersTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimate by remember {
        mutableStateOf( false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimate) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true) {
        startAnimate = true
        delay(4000)
        navController.navigate(NavRoute.Start.route)
    }
    Splash(alpha = alphaAnimation.value)

}

@Composable
fun Splash(alpha: Float) {

    Image(painter = painterResource(R.drawable.back),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        ) {
        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "Logotype",
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha)
        )

    }
}

@Composable
@Preview(showBackground = true)
fun preSplash() {
    AIPicPhotoFiltersTheme {
        Splash(1f)
    }
}