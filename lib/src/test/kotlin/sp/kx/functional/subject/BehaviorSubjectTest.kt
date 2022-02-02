package sp.kx.functional.subject

import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import sp.kx.functional.assertEquals
import sp.kx.functional.assertNotNull
import sp.kx.functional.subject.SubjectTest.Companion.assertSubscription
import sp.kx.functional.subject.SubjectTest.Companion.assertUnsubscribe

class BehaviorSubjectTest {
    @Test
    fun subscriptionTest() {
        BehaviorSubject<Int>().assertSubscription()
    }

    @Test
    fun unsubscribeTest() {
        BehaviorSubject<Int>().assertUnsubscribe()
    }

    @Test
    fun getValueOrNullTest() {
        val subject = BehaviorSubject<Int>()
        assertNull(subject.getValueOrNull())
        val expected = 1
        subject.next(expected)
        assertEquals(expected = expected, actual = subject.getValueOrNull().assertNotNull())
    }
}
