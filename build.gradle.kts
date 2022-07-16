// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        mavenCentral()
        google()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath(Dependencies.androidBuildTools)
        classpath(Dependencies.kotlinGradlePlugin)
        classpath(Dependencies.kotlinExt)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}


allprojects {
    repositories {
        mavenCentral()
        google()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.google.com") }
    }
}
