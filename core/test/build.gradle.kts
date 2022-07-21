plugins {
    `android-base-lib`
}

dependencies {
    koin()
    implementation(TestDependencies.JUnit.junit)
    implementation(TestDependencies.Compose.junit4)
}