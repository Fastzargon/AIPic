package com.freshlemonadeteamltd.aipicphotofilters


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.annotation.ExperimentalCoilApi
import com.freshlemonadeteamltd.aipicphotofilters.navigation.ArtNavHost
import com.freshlemonadeteamltd.aipicphotofilters.ui.theme.AIPicPhotoFiltersTheme
import com.freshlemonadeteamltd.aipicphotofilters.viewmodels.PhotoFilterViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber


@AndroidEntryPoint
@ExperimentalPagerApi
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity()
{
    val photoFilterViewModel : PhotoFilterViewModel by viewModels()
    private lateinit var analytics: FirebaseAnalytics

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Timber.i("Permission granted")
            shouldShowCamera.value = true
        } else {
            Timber.d("Permission denied")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        analytics = Firebase.analytics
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
            AIPicPhotoFiltersTheme {
                Scaffold(topBar = {
                        TopAppBar(
                            title = {
                                Image(painter = painterResource(R.drawable.logo),
                                    contentDescription = "Logotype",
                                    modifier = Modifier.size(40.dp))
                                Text(text = "AIPic", modifier = Modifier.padding(5.dp))
                            },
                            backgroundColor = colorResource(R.color.custom_black),
                            contentColor = colorResource(R.color.custom_yellow),
                            elevation = 15.dp
                        )
                },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background

                        ) {
                            ArtNavHost(photoFilterViewModel = photoFilterViewModel)
                            requestCameraPermission()


                        }
                    })

                }

            }


    }


    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }




}







@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AIPicPhotoFiltersTheme {

    }
}

@Composable
fun MainContent() {
    val systemUiController = rememberSystemUiController()
    // set navigation bar programmatically
    systemUiController.setNavigationBarColor(Color.Black)
}