package sp.kx.functional.subject

import org.junit.jupiter.api.Test
import sp.kx.functional.subject.SubjectTest.Companion.assertSubscription
import sp.kx.functional.subject.SubjectTest.Companion.assertUnsubscribe

class PublishSubjectTest {
    @Test
    fun subscriptionTest() {
        PublishSubject<Int>().assertSubscription()
    }

    @Test
    fun unsubscribeTest() {
        PublishSubject<Int>().assertUnsubscribe()
    }
}
