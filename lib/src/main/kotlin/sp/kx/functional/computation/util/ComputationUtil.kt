package sp.kx.functional.computation.util

import sp.kx.functional.computation.Completable

fun completed(block: () -> Unit): Completable {
    try {
        block()
    } catch (error: Throwable) {
        return Completable.Error(error)
    }
    return Completable.Success
}
