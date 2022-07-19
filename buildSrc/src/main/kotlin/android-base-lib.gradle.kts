import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.library")
}

android {
    compileSdkVersion = ProjectConfig.compileSdkVersion

    defaultConfig {
        minSdkPreview = ProjectConfig.minSdkVersion
        targetSdkPreview = ProjectConfig.targetSdkVersion

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        val sharedTestDir = "src/sharedTest/"
        val sharedTestSourceDir = "${sharedTestDir}java"
        val sharedTestResourceDir = "${sharedTestDir}resources"
        named("test") {
            java.srcDir(sharedTestSourceDir)
            resources.srcDir(sharedTestResourceDir)
        }
        named("androidTest") {
            java.srcDir(sharedTestSourceDir)
            resources.srcDir(sharedTestResourceDir)
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.compose
    }
}