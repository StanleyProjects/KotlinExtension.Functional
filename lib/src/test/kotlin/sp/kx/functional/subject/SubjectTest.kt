package sp.kx.functional.subject

import org.junit.jupiter.api.assertThrows
import sp.kx.functional.assertEquals
import sp.kx.functional.assertNotEquals

class SubjectTest {
    companion object {
        fun Subject<Int>.assertSubscription() {
            val init = 0
            var foo = init
            var bar = init
            var mutable = init
            next(++mutable)
            assertNotEquals(unexpected = mutable, actual = foo)
            assertNotEquals(unexpected = mutable, actual = bar)
            assertEquals(expected = init, actual = foo)
            assertEquals(expected = init, actual = bar)
            val fooSubscription = subscribe(Subject.action { foo = it })
            val barSubscription = subscribe(Subject.action { bar = it })
            next(++mutable)
            assertEquals(expected = mutable, actual = foo)
            assertEquals(expected = mutable, actual = bar)
            fooSubscription.unsubscribe()
            mutable.also { old ->
                next(++mutable)
                assertNotEquals(unexpected = old, actual = mutable)
                assertEquals(expected = mutable, actual = bar)
                assertNotEquals(unexpected = mutable, actual = foo)
                assertNotEquals(unexpected = bar, actual = foo)
                assertEquals(expected = old, actual = foo)
            }
            barSubscription.unsubscribe()
            mutable.also { old ->
                next(++mutable)
                assertNotEquals(unexpected = old, actual = mutable)
                assertNotEquals(unexpected = mutable, actual = foo)
                assertNotEquals(unexpected = mutable, actual = bar)
                assertNotEquals(unexpected = bar, actual = foo)
                assertEquals(expected = old, actual = bar)
            }
        }

        fun <T : Any> Subject<T>.assertUnsubscribe() {
            val subscription = subscribe(Subject.action {})
            subscription.unsubscribe()
            assertThrows<IllegalStateException> { subscription.unsubscribe() }
        }
    }
}
