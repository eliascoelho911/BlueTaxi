package com.github.eliascoelho911.bluetaxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueTaxiTheme {
                BlueTaxiNavGraph()
            }
        }
    }
}