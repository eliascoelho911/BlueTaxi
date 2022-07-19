import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion

fun BaseAppModuleExtension.appSetup() {
    projectDefaultConfig()



    projectBuildTypes()
    projectCompileOptions()
}

private fun BaseAppModuleExtension.projectDefaultConfig() {
    compileSdkVersion = ProjectConfig.compileSdkVersion

    defaultConfig {
        minSdkPreview = ProjectConfig.minSdkVersion
        targetSdkPreview = ProjectConfig.targetSdkVersion

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

private fun BaseAppModuleExtension.projectBuildTypes() {

}

private fun BaseAppModuleExtension.projectCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}