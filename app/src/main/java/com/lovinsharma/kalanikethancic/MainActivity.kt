package com.lovinsharma.kalanikethancic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lovinsharma.kalanikethancic.ui.theme.KalanikethanCICTheme
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            KalanikethanCICTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var selectedScreen by remember { mutableStateOf("Home") }



                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerContent(
                            selectedScreen = selectedScreen,
                            onScreenSelected = {
                                selectedScreen = it
                                scope.launch { drawerState.animateTo(DrawerValue.Closed, tween(500)) } // Close the drawer
                                navController.navigate(it)
                            }
                        )
                    }
                ) {
                    Scaffold { padding ->

                        IconButton(onClick = { scope.launch { drawerState.animateTo(DrawerValue.Open, tween(500)) } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                        Column(modifier = Modifier.padding(padding)) {
                            // Your main screen content
                            NavHost(navController, startDestination = "Home") {
                                composable("Home") { }
                                composable("SignIn") {  }
                                composable("AddScreen") {  }
                                composable("WhoInScreen") {  }
                                composable("History") {  }
                                composable("PaymentsScreen") {  }
                                composable("PaymentHistory") {  }
                            }
                        }











                    }
                }
            }
        }
    }
}


@Composable
fun DrawerContent(selectedScreen: String, onScreenSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        DrawerButton(
            iconResId = R.drawable.home,
            text = "Home",
            selectedScreen = selectedScreen,
            onClick = {
                onScreenSelected("Home")
            })
        DrawerButton(
            iconResId = R.drawable.login,
            text = "SignIn",
            selectedScreen = selectedScreen,
            onClick = {
                onScreenSelected("SignIn")
            }
        )
        DrawerButton(
            iconResId = R.drawable.add,
            text = "AddScreen",
            selectedScreen = selectedScreen,
            onClick = {
                onScreenSelected("AddScreen")}
        )
        DrawerButton(
            iconResId = R.drawable.search,
            text = "WhoInScreen",
            selectedScreen = selectedScreen,
            onClick = {
                onScreenSelected("WhoInScreen")}
        )
        DrawerButton(
            iconResId = R.drawable.history,
            text = "History",
            selectedScreen = selectedScreen,
            onClick = {
                onScreenSelected("History")}
        )
        DrawerButton(
            iconResId = R.drawable.wallet,
            text = "PaymentsScreen",
            selectedScreen = selectedScreen,
            onClick = {
                onScreenSelected("PaymentsScreen")}
        )
    }
}


@Composable
fun DrawerButton(iconResId: Int, text: String, selectedScreen: String,  onClick: () -> Unit)  {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (text == selectedScreen) MaterialTheme.colorScheme.secondary else Color.Transparent
        ),
        shape = MaterialTheme.shapes.medium // Use MaterialTheme's default button shape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Use CustomIcon composable to display the custom icon
            CustomIcon(iconResId = iconResId, contentDescription = text)

            Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing as needed

            Text(
                text = text,
                color = Color.White, // Text color
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 8.dp) // Add padding between icon and text
            )
        }
    }
}

@Composable
fun CustomIcon(iconResId: Int, contentDescription: String) {
    Icon(
        painter = painterResource(id = iconResId),
        contentDescription = contentDescription,
        modifier = Modifier.size(24.dp),
        tint = Color.White // Tint the icon white
    )
}
