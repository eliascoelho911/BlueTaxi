package com.bluetaxi.passenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bluetaxi.designsystem.theme.BlueTaxiTheme

class PassengerRootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueTaxiTheme {
                PassengerRootNavGraph()
            }
        }
    }
}