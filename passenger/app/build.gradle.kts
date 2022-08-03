plugins {
    `android-base-app`
}

android {
    defaultConfig {
        applicationId = "com.bluetaxi.passenger"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    koin()
    appCompat()
    composeNavigationAnimation()
    activityCompose()
    compose()

    designSystemModule()
    navigationModule()
    commonsModule()
    loginFeature()
}