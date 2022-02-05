package sp.kx.functional.computation

sealed interface Single<out T : Any> {
    class Success<out T : Any>(val value: T) : Single<T>
    class Error(val error: Throwable) : Single<Nothing>
}
