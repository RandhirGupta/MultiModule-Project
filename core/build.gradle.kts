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
}

dependencies {
  implementation(Libs.Android.Main.kotlinJdk7)
  implementation(Libs.Android.Main.kotlinCoroutine)
  implementation(Libs.Android.Main.ktxAndroid)
  implementation(Libs.Android.Main.appCompat)
  implementation(Libs.Android.Main.materialAndroid)
  implementation(Libs.Android.Main.constraintLayout)

  implementation(Libs.Android.Main.retrofit)
  implementation(Libs.Android.Main.moshi)
  implementation(Libs.Android.Main.moshiConverter)
  implementation(Libs.Android.Main.loggingInterceptor)

  api(Libs.Android.Main.dagger)
  kapt(Libs.Android.Main.daggerCompiler)

  testImplementation(Libs.Android.Test.jUnit)
  androidTestImplementation(Libs.Android.Test.androidXJunit)
  androidTestImplementation(Libs.Android.Test.espresso)
}
