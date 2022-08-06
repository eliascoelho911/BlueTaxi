import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.commons() {
    "implementation"(project(":commons:commons"))
}

fun DependencyHandlerScope.commonsAndroid() {
    "implementation"(project(":commons:android"))
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

fun DependencyHandlerScope.passengerLoginFeature() {
    "implementation"(project(":passenger:features:authentication:login"))
}

fun DependencyHandlerScope.passengerWelcomeFeature() {
    "implementation"(project(":passenger:features:authentication:welcome"))
}

fun DependencyHandlerScope.driverNavigation() {
    "implementation"(project(":driver:navigation"))
}

fun DependencyHandlerScope.driverLoginFeature() {
    "implementation"(project(":driver:features:authentication:login"))
}

fun DependencyHandlerScope.driverWelcomeFeature() {
    "implementation"(project(":driver:features:authentication:welcome"))
}