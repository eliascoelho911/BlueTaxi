import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.commonsModule() {
    "implementation"(project(":commons:commons"))
}

fun DependencyHandlerScope.coreModule() {
    "implementation"(project(":commons:core"))
}

fun DependencyHandlerScope.designSystemModule() {
    "implementation"(project(":designsystem"))
}

fun DependencyHandlerScope.navigationModule() {
    "implementation"(project(":navigation"))
}

fun DependencyHandlerScope.loginFeature() {
    "implementation"(project(":features:login"))
}

fun DependencyHandlerScope.welcomeFeature() {
    "implementation"(project(":features:welcome"))
}