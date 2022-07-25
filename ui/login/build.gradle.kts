plugins {
    `android-base-lib`
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    appCompat()
    compose()
    koin()

    unitTestDependencies()
    instrumentationTestDependencies()

    commonsModule()
    designSystemModule()
    domainModule()
}