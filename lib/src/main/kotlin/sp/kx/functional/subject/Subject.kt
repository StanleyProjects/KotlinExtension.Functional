package sp.kx.functional.subject

import sp.kx.functional.subscription.Subscription

interface SubjectAction<T : Any> {
    fun onNext(item: T)
}

interface SubjectConsumer<T : Any> {
    fun subscribe(action: SubjectAction<T>): Subscription
}

interface SubjectProducer<in T : Any> {
    infix fun next(item: T)
}

interface Subject<T : Any> : SubjectConsumer<T>, SubjectProducer<T> {
    companion object {
        fun <T : Any> action(onNext: (T) -> Unit): SubjectAction<T> {
            return SubjectActionImpl(actionOnNext = onNext)
        }
    }
}

private class SubjectActionImpl<T : Any>(private val actionOnNext: (T) -> Unit) : SubjectAction<T> {
    override fun onNext(item: T) {
        actionOnNext(item)
    }
}
