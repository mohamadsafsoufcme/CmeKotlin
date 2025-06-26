package com.cme.cmekotlin.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    val items = listOf(
        Pair(Icons.Filled.Home, "Home"),
        Pair(Icons.Filled.Store, "Store"),
        Pair(Icons.Filled.AttachMoney, "Entries"),
        Pair(Icons.Filled.Star, "Rewards"),
        Pair(Icons.Filled.AccountCircle, "My Account")
    )
    var selectedIndex by remember { mutableStateOf(0) }
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { selectedIndex = index },
                icon = { Icon(item.first, contentDescription = item.second) },
                label = { Text(item.second) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF20FF72),
                    selectedTextColor = Color(0xFF20FF72),
                    indicatorColor = Color.Black,
                    unselectedIconColor = Color(0xFF8B8B8B),
                    unselectedTextColor = Color(0xFF8B8B8B)
                )
            )
        }
    }
}
