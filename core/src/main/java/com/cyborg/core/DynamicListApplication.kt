package com.cyborg.core

import android.app.Application
import android.content.Context
import com.cyborg.core.di.CoreComponent
import com.cyborg.core.di.DaggerCoreComponent

class DynamicListApplication : Application() {

  lateinit var coreComponent: CoreComponent

  companion object {

    @JvmStatic
    fun coreComponent(context: Context): CoreComponent? = (context.applicationContext as? DynamicListApplication)?.coreComponent
  }

  override fun onCreate() {
    super.onCreate()

    initCoreDependencyInjection()
  }

  private fun initCoreDependencyInjection() {
    coreComponent = DaggerCoreComponent
      .builder()
      .application(this)
      .build()
  }
}
