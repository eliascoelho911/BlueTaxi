import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {
    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.Android.coreKtx}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.Android.activityCompose}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.Compose.compose}"
        const val material3 = "androidx.compose.material3:material3:${Versions.Compose.material3}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.Compose.compose}"
        const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.compose}"
        const val icons = "androidx.compose.material:material-icons-extended:${Versions.Compose.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.Compose.navigation}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.constraintLayout}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.Koin.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.Koin.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.Koin.koin}"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Versions.Google.gson}"
    }

    object Accompanist {
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.Accompanist.navigationAnimation}"
        const val systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:${Versions.Accompanist.navigationAnimation}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit2.retrofit}"
        const val converterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.Retrofit2.retrofit}"
    }

    object OkHttp {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.OkHttp3.okhttp}"
    }
}

fun DependencyHandlerScope.compose() {
    "implementation"(Dependencies.Compose.material3)
    "implementation"(Dependencies.Compose.ui)
    "implementation"(Dependencies.Compose.preview)
    "implementation"(Dependencies.Compose.icons)
    "debugImplementation"(Dependencies.Compose.uiTooling)
    "debugImplementation"("androidx.customview:customview:1.2.0-alpha01")
    "debugImplementation"("androidx.customview:customview-poolingcontainer:1.0.0-alpha01")
}

fun DependencyHandlerScope.constraintLayoutCompose() {
    "implementation"(Dependencies.Compose.constraintLayout)
}

fun DependencyHandlerScope.composeRuntime() {
    "implementation"(Dependencies.Compose.runtime)
}

fun DependencyHandlerScope.koin() {
    "implementation"(Dependencies.Koin.core)
    "implementation"(Dependencies.Koin.android)
    "implementation"(Dependencies.Koin.compose)
}

fun DependencyHandlerScope.appCompat() {
    "implementation"(Dependencies.Android.appCompat)
}

fun DependencyHandlerScope.viewModel() {
    "implementation"(Dependencies.Android.viewModel)
}

fun DependencyHandlerScope.composeNavigation() {
    "implementation"(Dependencies.Compose.navigation)
}

fun DependencyHandlerScope.composeNavigationAnimation() {
    "implementation"(Dependencies.Accompanist.navigationAnimation)
}

fun DependencyHandlerScope.systemUiController() {
    "implementation"(Dependencies.Accompanist.systemuicontroller)
}

fun DependencyHandlerScope.activityCompose() {
    "implementation"(Dependencies.Android.activityCompose)
}