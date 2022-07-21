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

    test()

    commonsModule()
    designSystemModule()
}