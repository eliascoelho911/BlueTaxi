import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object TestDependencies {

    object Kotlin {
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
    }

    object JUnit {
        const val junit = "junit:junit:${Versions.JUnit.junit}"
    }

    object Android {
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.Android.coreTesting}"
        const val runner = "androidx.test:runner:${Versions.Android.testRunner}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Android.espresso}"
        const val junitExt = "androidx.test.ext:junit:${Versions.Android.junitExt}"
        const val coreKtx = "androidx.test:core-ktx:${Versions.Android.coreKtx}"
        const val rules = "androidx.test:rules:${Versions.Android.testRules}"
        const val fragmentTesting =
            "androidx.fragment:fragment-testing:${Versions.Android.fragmentTesting}"
    }

    object Robolectric {
        const val robolectric = "org.robolectric:robolectric:${Versions.Robolectric.robolectric}"
        const val annotations = "org.robolectric:annotations:${Versions.Robolectric.robolectric}"
    }

    object OkHttp3 {
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.OkHttp3.okhttp}"
    }

    object MockK {
        const val mockK = "io.mockk:mockk:${Versions.MockK.mockK}"
        const val android = "io.mockk:mockk-android:${Versions.MockK.mockK}"
    }

    object Koin {
        const val koinTest = "io.insert-koin:koin-test:${Versions.Koin.koin}"
        const val koinTestJunit4 = "io.insert-koin:koin-test-junit4:${Versions.Koin.koin}"
    }

    object Compose {
        const val junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.compose}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.Compose.compose}"
    }
}

fun DependencyHandlerScope.unitTestDependencies() {
    "testImplementation"(TestDependencies.JUnit.junit)
    "testImplementation"(TestDependencies.MockK.mockK)
}

fun DependencyHandlerScope.instrumentationTestDependencies() {
    "testImplementation"(TestDependencies.Robolectric.robolectric)
    "testImplementation"(TestDependencies.Koin.koinTest)
    "testImplementation"(TestDependencies.Koin.koinTestJunit4)
    "testImplementation"(TestDependencies.Compose.junit4)
    "testImplementation"(TestDependencies.Android.rules)
    "testImplementation"(TestDependencies.Android.runner)
    "testImplementation"(TestDependencies.Android.espressoCore)
    "testImplementation"(TestDependencies.Android.junitExt)
    "testImplementation"(TestDependencies.Android.coreKtx)
    "testImplementation"(TestDependencies.Android.coreTesting)
    "testImplementation"(TestDependencies.Kotlin.coroutinesTest)
    "testImplementation"(project(":commons:test"))
    "debugImplementation"(TestDependencies.Android.fragmentTesting)
    "debugImplementation"(TestDependencies.Compose.uiTestManifest)
    "androidTestImplementation"(TestDependencies.Android.junitExt)
    "androidTestImplementation"(TestDependencies.Android.espressoCore)
    "androidTestImplementation"(TestDependencies.Android.coreKtx)
    "androidTestImplementation"(TestDependencies.Android.coreTesting)
    "androidTestImplementation"(TestDependencies.Compose.junit4)
    "androidTestImplementation"(TestDependencies.Android.rules)
    "androidTestImplementation"(TestDependencies.Android.runner)
    "androidTestImplementation"(TestDependencies.MockK.android)
    "androidTestImplementation"(TestDependencies.Robolectric.annotations)
    "androidTestImplementation"(project(":commons:test"))
}