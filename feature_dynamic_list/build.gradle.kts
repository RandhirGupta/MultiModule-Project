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

  implementation(project(Libs.Modules.core))
  implementation(project(Libs.Modules.presentation))

  implementation(Libs.Android.Main.ktxAndroid)
  implementation(Libs.Android.Main.appCompat)
  implementation(Libs.Android.Main.materialAndroid)
  implementation(Libs.Android.Main.constraintLayout)
  implementation(Libs.Android.Main.kotlinCoroutine)
  implementation(Libs.Android.Main.viewModelKtx)


  api(Libs.Android.Main.lifecycleVersionExtension)
  kapt(Libs.Android.Main.lifeCycleCompiler)

  implementation(Libs.Android.Main.moshi)
  implementation(Libs.Android.Main.moshiAdapter)
  implementation(Libs.Android.Main.moshiKotlin)

  api(Libs.Android.Main.dagger)
  kapt(Libs.Android.Main.daggerCompiler)
}
