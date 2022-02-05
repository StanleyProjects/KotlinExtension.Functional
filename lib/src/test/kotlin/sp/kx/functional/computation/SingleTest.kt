package sp.kx.functional.computation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sp.kx.functional.assertType
import sp.kx.functional.computation.util.coroutine.singled
import sp.kx.functional.computation.util.singled

class SingleTest {
    companion object {
        private fun <T : Any> successComputation(value: T): T {
            Thread.sleep(250)
            return value
        }
    }

    @Test
    fun singledSuccessTest() {
        val expected = hashCode()
        val result = singled {
            successComputation(expected)
        }
        val actual = result.assertType<Single.Success<Int>>().value
        assertEquals(expected, actual)
    }

    @Test
    fun singledErrorTest() {
        val expected = IllegalStateException("expected message")
        val result = singled {
            throw expected
        }
        val actual = result.assertType<Single.Error>().error
        assertEquals(expected, actual)
    }

    @Test
    fun singledCoroutineSuccessTest() {
        val expected = hashCode()
        val result = runBlocking {
            singled(Dispatchers.IO) {
                successComputation(expected)
            }
        }
        val actual = result.assertType<Single.Success<Int>>().value
        assertEquals(expected, actual)
    }

    @Test
    fun singledCoroutineErrorTest() {
        val expected = IllegalStateException("expected message")
        val result = runBlocking {
            singled(Dispatchers.IO) {
                throw expected
            }
        }
        val actual = result.assertType<Single.Error>().error
        assertEquals(expected, actual)
    }
}
