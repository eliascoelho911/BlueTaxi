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
    }
}

//todo Separar implementação de testes unitários, de testes instrumentados
fun DependencyHandlerScope.test() {
    "testImplementation"(TestDependencies.Robolectric.robolectric)
    "testImplementation"(TestDependencies.JUnit.junit)
    "testImplementation"(TestDependencies.Android.junitExt)
    "testImplementation"(TestDependencies.Android.espressoCore)
    "testImplementation"(TestDependencies.Android.coreKtx)
    "testImplementation"(TestDependencies.Android.coreTesting)
    "testImplementation"(TestDependencies.Kotlin.coroutinesTest)
    "testImplementation"(TestDependencies.Android.runner)
    "testImplementation"(TestDependencies.MockK.mockK)
    "testImplementation"(TestDependencies.Koin.koinTest)
    "testImplementation"(TestDependencies.Koin.koinTestJunit4)
    "testImplementation"(TestDependencies.Compose.junit4)
    "testImplementation"(TestDependencies.Android.rules)
    "testImplementation"(project(":core:test"))
    "debugImplementation"(TestDependencies.Android.fragmentTesting)
    "androidTestImplementation"(TestDependencies.Android.runner)
    "androidTestImplementation"(TestDependencies.MockK.android)
    "androidTestImplementation"(TestDependencies.Robolectric.annotations)
}