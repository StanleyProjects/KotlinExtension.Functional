package sp.kx.functional.subject

import sp.kx.functional.subscription.Subscription

class BehaviorSubject<T : Any> : Subject<T> {
    private val subjectActions = mutableSetOf<SubjectAction<T>>()

    private inner class InnerSubscription(action: SubjectAction<T>) : Subscription {
        private var action: SubjectAction<T>? = action

        override fun unsubscribe() {
            subjectActions.remove(action ?: error("Already unsubscribed!"))
            action = null
        }
    }

    private var value: T? = null

    fun getValueOrNull(): T? {
        return value
    }

    override fun subscribe(action: SubjectAction<T>): Subscription {
        subjectActions.add(action)
        value?.also(action::onNext)
        return InnerSubscription(action)
    }

    override fun next(item: T) {
        value = item
        subjectActions.forEach { it.onNext(item) }
    }
}
