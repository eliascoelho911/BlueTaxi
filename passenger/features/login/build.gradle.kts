plugins {
    `android-base-lib`
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    compose()
    koin()
    navigation()

    loginFeature()
}