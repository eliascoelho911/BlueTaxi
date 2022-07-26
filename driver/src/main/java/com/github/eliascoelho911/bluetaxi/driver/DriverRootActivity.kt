package com.github.eliascoelho911.bluetaxi.driver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme

class DriverRootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueTaxiTheme {
                PassengerRootNavGraph()
            }
        }
    }
}