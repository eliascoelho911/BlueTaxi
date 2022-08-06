plugins {
    `android-base-lib`
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    composeRuntime()
    composeNavigation()
    composeNavigationAnimation()
}