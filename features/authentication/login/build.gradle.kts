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
    orbit()
    composeNavigation()
    authenticationDomain()
    commons()
    designSystem()

    unitTestDependencies()
    instrumentationTestDependencies()
}