package sp.kx.functional.computation.util.coroutine

import kotlinx.coroutines.withContext
import sp.kx.functional.computation.Completable
import sp.kx.functional.computation.util.completed
import kotlin.coroutines.CoroutineContext

suspend fun completed(
    context: CoroutineContext,
    block: () -> Unit
): Completable {
    return withContext(context) {
        completed(block)
    }
}
