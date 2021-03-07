package jp.devtact.sentakun

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: start")
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.button_navigation)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        Log.d(TAG, "onCreate: end")
    }
}