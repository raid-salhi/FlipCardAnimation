package com.example.flipcardanimation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                       // We separated the main code in this FlippingCard composable func
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

    // this boolean indicates the state of the card if it is in the face or the tale
    var rotated by remember {
        mutableStateOf(false)
    }

    // this float state is the one responsible of flipping (animating) our card from 0 degree to 180 degree or the opposite
    val rotate by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        // we specify the animation duration to 0.6 sec
        animationSpec = tween(600),
    )

    // this is the card container ui code
    Card (
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffEFEFEF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(320.dp)
            // graphicsLayer is a modifier's method which is the one responsible for applying various effect to the content
            // we're going to apply the rotation effect (flipping the card)
            .graphicsLayer {
                // we'll rotate the card on the horizontal axis using the float state we created
                rotationY = rotate
                // this line is to reduce the zoom-in effect
                cameraDistance = 10 * density
            }
            .fillMaxWidth()
    ){

        // here ww are checking if the rotation value is less than 90 degree, means the face must be visible
        // else we show the tale
        if (rotate < 90f)
            CardFace {
                // when onclick method is triggered we change the rotated boolean value to indicates that we're changing the tale
                // when the boolean value change it will trigger the float state that it responsible of the rotation
                rotated = !rotated
            }

        else
            CardTale {
                // when onclick method is triggered we rotate the card from tale to face
                rotated = !rotated
            }


    }
}
// Card Tale ui design
@Composable
fun CardTale( onClick: () -> Unit) {

    // this val is responsible for eliminating the mirror effect applied when going from card face to card tale
    val rotateText = 180f

    Column(
        modifier = Modifier
            .fillMaxSize()
            // we invoke onclick method when we click on the card
            .clickable { onClick.invoke() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xff0F0F0F))
        )
        Text(
            text = "123",
            color = Color(0xff0F0F0F),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                // here we prevent the mirror effect applied on the text caused by the rotation
                .graphicsLayer {
                    rotationY = rotateText
                }
                .background(Color.White),
            )
    }
}

// Card face ui design
@Composable
fun CardFace(onClick: () -> Unit) {

    Box(
        Modifier
            .fillMaxWidth()
            // we invoke onclick method when we click on the card
            .clickable { onClick.invoke() }
    ) {

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
