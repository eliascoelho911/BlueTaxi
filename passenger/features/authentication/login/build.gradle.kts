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
    loginFeature()
    authenticationDomain()
    passengerNavigation()
    composeNavigation()
}