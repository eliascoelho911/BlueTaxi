import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.designSystemModule() {
    "implementation"(project(":designsystem"))
}

fun DependencyHandlerScope.commonsModule() {
    "implementation"(project(":commons"))
}

fun DependencyHandlerScope.authModule() {
    "implementation"(project(":auth"))
}