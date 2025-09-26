package net.kingchev.kaid.test

import net.kingchev.kaid.resource.env
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class EnvironmentTest {
    @Test
    fun `testing js delegates`() {
        val key1: String by env("KEY_ONE")
        val expect1 = "VALUE_ONE"
        assertEquals(expect1, key1)

        val key2: Int by env("KEY_TWO") { it.toInt() }
        val expect2 = 1
        assertEquals(expect2, key2)
    }

    @Test
    fun `testing non exist key`() {
        val nonExistKey: String by env("KEY_ONE")
        assertFails { assertEquals(nonExistKey, "NON_EXIST") }
    }
}