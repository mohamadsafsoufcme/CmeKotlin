package com.cme.cmekotlin.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Store
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavBar() {
    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        NavigationBarItem(selected = true, onClick = {}, icon = {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }, label = { Text("Home") })

        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(Icons.Filled.Store, contentDescription = "Store")
        }, label = { Text("Store") })

        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(Icons.Filled.AttachMoney, contentDescription = "My Entries")
        }, label = { Text("My Entries") })

        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(Icons.Filled.Star, contentDescription = "Rewards")
        }, label = { Text("Rewards") })

        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(Icons.Filled.AccountCircle, contentDescription = "Account")
        }, label = { Text("My Account") })
    }
}
