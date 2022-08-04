import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.commons() {
    "implementation"(project(":commons:commons"))
}

fun DependencyHandlerScope.designSystem() {
    "implementation"(project(":designsystem"))
}

fun DependencyHandlerScope.loginFeature() {
    "implementation"(project(":features:login"))
}

fun DependencyHandlerScope.welcomeFeature() {
    "implementation"(project(":features:welcome"))
}