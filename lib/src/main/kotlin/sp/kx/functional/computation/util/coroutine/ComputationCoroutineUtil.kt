package sp.kx.functional.computation.util.coroutine

import kotlinx.coroutines.withContext
import sp.kx.functional.computation.Completable
import sp.kx.functional.computation.Single
import sp.kx.functional.computation.util.completed
import sp.kx.functional.computation.util.singled
import kotlin.coroutines.CoroutineContext

suspend fun completed(
    context: CoroutineContext,
    block: () -> Unit
): Completable {
    return withContext(context) {
        completed(block)
    }
}

suspend fun <T : Any> singled(
    context: CoroutineContext,
    block: () -> T
): Single<T> {
    return withContext(context) {
        singled(block)
    }
}
