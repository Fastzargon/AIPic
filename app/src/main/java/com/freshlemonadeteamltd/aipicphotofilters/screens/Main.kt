package com.freshlemonadeteamltd.aipicphotofilters.screens



import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.freshlemonadeteamltd.aipicphotofilters.R
import com.freshlemonadeteamltd.aipicphotofilters.camera.CameraCapture
import com.freshlemonadeteamltd.aipicphotofilters.navigation.NavRoute
import com.freshlemonadeteamltd.aipicphotofilters.ui.theme.AIPicPhotoFiltersTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi



val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun Main(navController: NavHostController) {
    Image(painter = painterResource(R.drawable.back), //BG image
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) {
            uri: Uri? ->
        imageUri = uri
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(colorResource(R.color.custom_black)),
            contentAlignment = Alignment.TopEnd
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = { launcher.launch("image/*")},
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.custom_black)),
                    modifier = Modifier
                        .width(54.dp)
                        .height(54.dp),
                    shape = CircleShape
                ) {
                    Image(painter = painterResource(R.drawable.gallery_icon),
                        contentDescription = "Gallery_icon",
                        Modifier.size(40.dp)
                    )
                }


                Button(
                    onClick = { navController.navigate(route = NavRoute.MainContent.route) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.custom_black)),
                    modifier = Modifier
                        .width(54.dp)
                        .height(54.dp),
                    shape = CircleShape
                ) {
                    Image(painter = painterResource(R.drawable.camera_icon),
                        contentDescription = "Camera_icon",
                        Modifier.size(35.dp)
                    )
                }


                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.custom_black)),
                    modifier = Modifier
                        .width(54.dp)
                        .height(54.dp),
                    shape = CircleShape
                ) {
                    Image(painter = painterResource(R.drawable.download_icon),
                        contentDescription = "Download_icon",
                        Modifier.size(30.dp)
                    )
                }
            }
        }

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver,it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver,it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let {  btm ->
                Box(modifier = Modifier.fillMaxSize())
                {
                    Image(bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 80.dp, top = 5.dp)
                    )

                }

            }
        }
    }




}






@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun MainContent(navController: NavHostController,modifier: Modifier = Modifier) {
    var imageUri by remember { mutableStateOf(EMPTY_IMAGE_URI) }
    if (imageUri != EMPTY_IMAGE_URI) {
        Box(modifier = modifier.background(Color.Black)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(imageUri),
                contentDescription = "Captured image"
            )

        }
    } else {
        Box(modifier = modifier) {
            CameraCapture(
                modifier = modifier,
                onImageFile = { file ->
                    imageUri = file.toUri()
                }
            )


        }

    }
}








@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    AIPicPhotoFiltersTheme {
        Main(navController = rememberNavController())
    }
}