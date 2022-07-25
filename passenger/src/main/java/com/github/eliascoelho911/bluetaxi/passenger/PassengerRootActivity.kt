package com.github.eliascoelho911.bluetaxi.passenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme

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