import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.commons() {
    "implementation"(project(":commons:commons"))
}

fun DependencyHandlerScope.designSystem() {
    "implementation"(project(":designsystem"))
}

fun DependencyHandlerScope.loginFeature() {
    "implementation"(project(":features:authentication:login"))
}

fun DependencyHandlerScope.authenticationDomain() {
    "implementation"(project(":features:authentication:shared-domain"))
}

fun DependencyHandlerScope.welcomeFeature() {
    "implementation"(project(":features:authentication:welcome"))
}

fun DependencyHandlerScope.passengerNavigation() {
    "implementation"(project(":passenger:navigation"))
}