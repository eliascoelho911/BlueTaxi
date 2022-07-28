plugins {
    `android-base-lib`
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    composeNavigationAnimation()

    uiLoginModule()
    uiWelcomeModule()
}