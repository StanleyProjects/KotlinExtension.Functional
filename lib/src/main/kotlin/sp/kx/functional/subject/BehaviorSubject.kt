package sp.kx.functional.subject

import sp.kx.functional.subscription.Subscription

class BehaviorSubject<T : Any> : Subject<T> {
    private inner class BehaviorSubjectSubscription(action: SubjectAction<T>) : Subscription {
        private var action: SubjectAction<T>? = action

        override fun unsubscribe() {
            val action = this.action
            checkNotNull(action)
            subjectActions.remove(action)
            this.action = null
        }
    }

    private val subjectActions = mutableSetOf<SubjectAction<T>>()

    private var value: T? = null

    fun getValueOrNull(): T? {
        return value
    }

    override fun subscribe(action: SubjectAction<T>): Subscription {
        subjectActions.add(action)
        value?.also(action::onNext)
        return BehaviorSubjectSubscription(action)
    }

    override fun next(item: T) {
        value = item
        subjectActions.forEach { it.onNext(item) }
    }
}
