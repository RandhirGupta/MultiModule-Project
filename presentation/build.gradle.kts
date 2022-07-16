plugins {
  id(Plugins.androidLibrary)
  id(Plugins.kotlinAndroid)
  id(Plugins.kotlinKapt)
  id(Plugins.kotlinAndroidExtensions)
}

android {
  compileSdk = AndroidConfig.compileSdkVersion

  defaultConfig {
    minSdk = AndroidConfig.minSdkVersion
    targetSdk = AndroidConfig.targetSdkVersion

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  buildFeatures {
    dataBinding = true
    viewBinding = true
  }
}

dependencies {

  implementation(Libs.Android.Main.ktxAndroid)
  implementation(Libs.Android.Main.appCompat)
  implementation(Libs.Android.Main.materialAndroid)
  implementation(Libs.Android.Main.cardView)
  implementation(Libs.Android.Main.recyclerView)
  implementation(Libs.Android.Main.constraintLayout)
  implementation(Libs.Android.Main.kotlinCoroutine)
  implementation(Libs.Android.Main.viewModelKtx)
  implementation(Libs.Android.Main.navigationFragment)
  implementation(Libs.Android.Main.navigation)
  implementation(Libs.Android.Main.navigationFragmentKtx)
  implementation(Libs.Android.Main.navigationKtx)

  api(Libs.Android.Main.lifecycleVersionExtension)
  kapt(Libs.Android.Main.lifeCycleCompiler)

  api(Libs.Android.Main.dagger)
  kapt(Libs.Android.Main.daggerCompiler)
}
