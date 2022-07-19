plugins {
    `android-base-app`
}

android {
    defaultConfig {
        applicationId = "com.github.eliascoelho911.bluetaxi"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
}

dependencies {
    koin()
    appCompat()
    navigationAnimation()
    activityCompose()
    
    designSystemModule()
    coreModule()
    authModule()
}