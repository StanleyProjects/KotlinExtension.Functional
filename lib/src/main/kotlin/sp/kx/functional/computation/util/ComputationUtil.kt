package sp.kx.functional.computation.util

import sp.kx.functional.computation.Completable
import sp.kx.functional.computation.Single

fun completed(block: () -> Unit): Completable {
    try {
        block()
    } catch (error: Throwable) {
        return Completable.Error(error)
    }
    return Completable.Success
}

fun <T : Any> singled(block: () -> T): Single<T> {
    val result = try {
        block()
    } catch (e: Throwable) {
        return Single.Error(e)
    }
    return Single.Success(result)
}

