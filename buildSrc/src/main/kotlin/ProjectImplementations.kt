import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.designSystemModule() {
    "implementation"(project(":designsystem"))
}

fun DependencyHandlerScope.commonsModule() {
    "implementation"(project(":commons"))
}

fun DependencyHandlerScope.domainModule() {
    "implementation"(project(":domain"))
}

fun DependencyHandlerScope.navigationModule() {
    "implementation"(project(":navigation"))
}

fun DependencyHandlerScope.uiLoginModule() {
    "implementation"(project(":ui:login"))
}