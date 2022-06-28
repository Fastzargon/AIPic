package com.freshlemonadeteamltd.aipicphotofilters.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.freshlemonadeteamltd.aipicphotofilters.R
import com.freshlemonadeteamltd.aipicphotofilters.navigation.NavRoute
import com.freshlemonadeteamltd.aipicphotofilters.ui.theme.AIPicPhotoFiltersTheme
import com.freshlemonadeteamltd.aipicphotofilters.view.ViewPagerSlider
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun Start(navController: NavHostController)
{
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(R.drawable.back),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            ViewPagerSlider()
        }

        /*Box(
            Modifier.padding(70.dp),
            contentAlignment = Alignment.TopCenter,
        ){
            Row(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,


            ) {

                Image(painter = painterResource(R.drawable.logo_text_next),
                    contentDescription = "Logotype",
                    modifier = Modifier
                        .padding(0.dp)
                        .size(200.dp)
                )

            }

        }*/


        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Button(
                onClick = { navController.navigate(route = NavRoute.Main.route) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black,
                    contentColor = Color.Yellow),
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(vertical = 100.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(text = "Let's Start",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

            }
        }



    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    AIPicPhotoFiltersTheme {
        Start(navController = rememberNavController())
    }
}
