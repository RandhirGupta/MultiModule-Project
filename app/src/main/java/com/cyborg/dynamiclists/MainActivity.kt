package com.cyborg.dynamiclists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

  private val navController by lazy { Navigation.findNavController(this, R.id.navHostFragment) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    navController.navigateUp()
  }
}
