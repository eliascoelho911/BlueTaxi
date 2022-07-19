buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(BuildPlugins.gradle)
        classpath(BuildPlugins.kotlin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}