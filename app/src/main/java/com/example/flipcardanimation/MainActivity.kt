package com.example.flipcardanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flipcardanimation.ui.theme.FlipCardAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlipCardAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                   Column(verticalArrangement = Arrangement.Center) {
                       FlippingCard()
                   }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlippingCard(){
    var rotated by remember {
        mutableStateOf(false)
    }
    Card (
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffEFEFEF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ){
        if (rotated)
            CardFace()
        else
            CardTale()
    }
}

@Composable
fun CardTale() {
    TODO("Not yet implemented")
}

@Composable
fun CardFace() {
    Box(Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.circles),
            contentDescription = "circles",
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Column (
            modifier= Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chip),
                    contentDescription ="chip",
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = R.drawable.wifi),
                    contentDescription ="chip",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            Column {
                Text(
                    text = "3455 4562 7710 3507",
                    color = Color(0xff0F0F0F),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Box(
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .background(Color(0xff0F0F0F))
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "John Carter",
                        color = Color(0xff0F0F0F),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "02/30",
                        color = Color(0xff0F0F0F),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

        }
    }
}
