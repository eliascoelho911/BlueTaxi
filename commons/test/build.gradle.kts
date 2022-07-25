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
    implementation(TestDependencies.Android.rules)
    implementation(TestDependencies.Compose.junit4)
    implementation(TestDependencies.Android.coreKtx)
    implementation(TestDependencies.Android.coreTesting)
}