package uk.ac.tees.mad.stayfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uk.ac.tees.mad.stayfinder.ui.screens.auth.AuthScreen
import uk.ac.tees.mad.stayfinder.ui.theme.StayFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StayFinderTheme {
                AuthScreen {

                }
            }
        }
    }
}
