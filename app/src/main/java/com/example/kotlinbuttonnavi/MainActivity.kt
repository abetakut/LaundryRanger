package com.example.kotlinbuttonnavi

import Communicator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinbuttonnavi.ui.home.HomeFragment
import com.example.kotlinbuttonnavi.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: start")
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        val settingFragment = SettingFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,settingFragment).commit()

        Log.d(TAG, "onCreate: end")
    }

    override fun passDataCom(editTextInput: String) {
        Log.d(TAG, "passDataCom: start")
        val bundle = Bundle()
        val transaction = this.supportFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()

        bundle.putString("PostCode", editTextInput)
        homeFragment.arguments = bundle

        transaction.replace(R.id.nav_host_fragment, homeFragment).commit()
        Log.d(TAG, "passDataCom: end")
    }

}