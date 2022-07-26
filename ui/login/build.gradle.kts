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

    unitTestDependencies()
    instrumentationTestDependencies()

    commonsModule()
    designSystemModule()
    domainModule()
}