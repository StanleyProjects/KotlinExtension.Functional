package sp.kx.functional.computation

sealed interface Completable {
    object Success : Completable
    class Error(val error: Throwable) : Completable
}
