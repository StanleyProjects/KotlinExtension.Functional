package sp.kx.functional.subject

import sp.kx.functional.subscription.Subscription

class PublishSubject<T : Any> : Subject<T> {
    private val subjectActions = mutableSetOf<SubjectAction<T>>()

    private inner class InnerSubscription(action: SubjectAction<T>) : Subscription {
        private var action: SubjectAction<T>? = action

        override fun unsubscribe() {
            subjectActions.remove(action ?: error("Already unsubscribed!"))
            action = null
        }
    }

    override fun subscribe(action: SubjectAction<T>): Subscription {
        subjectActions.add(action)
        return InnerSubscription(action)
    }

    override fun next(item: T) {
        subjectActions.forEach { it.onNext(item) }
    }
}
