package sp.kx.functional

import org.junit.jupiter.api.Assertions

fun assertEquals(expected: Int, actual: Int) {
    Assertions.assertEquals(expected, actual)
}

fun assertNotEquals(unexpected: Int, actual: Int) {
    Assertions.assertNotEquals(unexpected, actual)
}

fun Int?.assertNotNull(): Int {
    Assertions.assertNotNull(this)
    if (this == null) error("Impossible!")
    return this
}
