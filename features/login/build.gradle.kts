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

    unitTestDependencies()
    instrumentationTestDependencies()

    commons()
    designSystem()
}