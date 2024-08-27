package vn.edu.usth.usthweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vn.edu.usth.usthweather.ui.theme.USTHWeatherTheme

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            USTHWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStop(){
        super.onStop()
        Log.i("success","Stopped")
    }
    override fun onResume(){
        super.onResume()
        Log.i("success","Resumed")
    }
    override fun onPause(){
        super.onPause()
        Log.i("success","Paused")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i("success","Destroyed")
    }
    override fun onStart(){
        super.onStart()
        Log.i("success","Started")
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    USTHWeatherTheme {
        Greeting("Android")
    }
}

