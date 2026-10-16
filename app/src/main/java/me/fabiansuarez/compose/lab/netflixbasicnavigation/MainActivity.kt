package me.fabiansuarez.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import me.fabiansuarez.compose.lab.netflixbasicnavigation.navigation.NetflixNavHost
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.NetflixBasicNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetflixBasicNavigationTheme {
                NetflixNavHost()
            }
        }
    }
}
