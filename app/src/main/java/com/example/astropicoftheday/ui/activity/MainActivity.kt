package com.example.astropicoftheday.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.astropicoftheday.R
import com.example.astropicoftheday.ui.bottomnav.BottomNavItem
import com.example.astropicoftheday.ui.bottomnav.BottomNavigationBar
import com.example.astropicoftheday.ui.composelibrary.screens.PetHomeScreen
import com.example.astropicoftheday.ui.theme.AstroAndCatTheme
import com.example.astropicoftheday.ui.viewmodel.PetCardsViewModel

class MainActivity : ComponentActivity() {

    private val petsViewModel: PetCardsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstroAndCatTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Favorite",
                                    route = "favorite",
                                    icon = Icons.Default.Favorite,
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    },
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.wrapContentHeight(),
                            title = {
                                Text(text = "CutiePet", textAlign = TextAlign.Center)
                            },
                            navigationIcon = {
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.cat_and_dog_logo),
                                    contentDescription = null
                                )
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.tertiary
                            )
                        )
                    }
                ) {
                    PetHomeScreen(petsViewModel = petsViewModel, it)
                }
            }
        }
    }
}
