package com.bluetaxi.test

import kotlin.math.exp
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.OrbitVerification
import org.orbitmvi.orbit.SuspendingTestContainerHost
import org.orbitmvi.orbit.test

inline fun <STATE : Any, SIDE_EFFECT : Any, CONTAINER_HOST : ContainerHost<STATE, SIDE_EFFECT>> CONTAINER_HOST.runOrbitTest(
    initialState: STATE? = null,
    isolateFlow: Boolean = true,
    settings: Container.Settings = container.settings,
    block: SuspendingTestContainerHost<STATE, SIDE_EFFECT, CONTAINER_HOST>.() -> Unit,
) {
    test(initialState, isolateFlow, settings).block()
}

fun <STATE: Any> OrbitVerification<STATE, *>.state(expectedStateChange: STATE.() -> STATE) {
    states(expectedStateChange)
}