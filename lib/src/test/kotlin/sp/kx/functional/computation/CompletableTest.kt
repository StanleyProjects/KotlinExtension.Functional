package sp.kx.functional.computation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sp.kx.functional.assertType
import sp.kx.functional.computation.util.completed
import sp.kx.functional.computation.util.coroutine.completed

class CompletableTest {
    companion object {
        private fun successComputation() {
            Thread.sleep(250)
        }
    }

    @Test
    fun completedSuccessTest() {
        val result = completed {
            successComputation()
        }
        result.assertType<Completable.Success>()
    }

    @Test
    fun completedErrorTest() {
        val expected = IllegalStateException("expected message")
        val result = completed {
            throw expected
        }
        val actual = result.assertType<Completable.Error>().error
        assertEquals(expected, actual)
    }

    @Test
    fun completedCoroutineSuccessTest() {
        val result = runBlocking {
            completed(Dispatchers.IO) {
                successComputation()
            }
        }
        result.assertType<Completable.Success>()
    }

    @Test
    fun completedCoroutineErrorTest() {
        val expected = IllegalStateException("expected message")
        val result = runBlocking {
            completed(Dispatchers.IO) {
                throw expected
            }
        }
        val actual = result.assertType<Completable.Error>().error
        assertEquals(expected, actual)
    }
}
