package net.kingchev.kaid.test

import net.kingchev.kaid.resource.property
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class Property {
    @Test
    fun `testing jvm delegates`() {
        val expect1: String by property("prop1")
        val actual1 = "value one"
        assertEquals(expect1, actual1)

        val expect2: Int by property("prop2") { it.toInt() }
        val actual2 = 1
        assertEquals(expect2, actual2)
    }

    @Test
    fun `testing non exist key`() {
        val nonExistKey: String by property("non.exist")
        assertFails { assertEquals(nonExistKey, "NON_EXIST") }
    }
}