import java.util.*

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        applicationId = "com.cyborg.dynamiclists"
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
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
    implementation(project(Libs.Modules.featureDynamicList))

    implementation(Libs.Android.Main.appCompat)
    implementation(Libs.Android.Main.constraintLayout)
    implementation(Libs.Android.Main.navigationFragment)
    implementation(Libs.Android.Main.navigation)
    implementation(Libs.Android.Main.navigationFragmentKtx)
    implementation(Libs.Android.Main.navigationKtx)
}
