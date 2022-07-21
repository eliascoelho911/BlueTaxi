plugins {
    `android-base-lib`
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    koin()
    implementation(TestDependencies.JUnit.junit)
    implementation(TestDependencies.Compose.junit4)
}