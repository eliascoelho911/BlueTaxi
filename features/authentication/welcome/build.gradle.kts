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
    constraintLayoutCompose()
    designSystem()

    unitTestDependencies()
    instrumentationTestDependencies()
}