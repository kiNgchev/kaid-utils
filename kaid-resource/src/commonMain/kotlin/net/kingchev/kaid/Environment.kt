@file:Suppress("UNCHECKED_CAST")

package net.kingchev.kaid

import kotlin.reflect.KProperty

public class Environment<T>(
    private val key: String,
    private val default: T?,
    private val block: (String) -> T
) {
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val result = System.getenv(key)

        if (result == null && default == null)
            throw NullPointerException("This environment value is not defined for $key")
        else if (result == null && default != null)
            return default

        return block(result)
    }

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
            = throw UnsupportedOperationException("You may not change this value")
}

public fun <T> env(key: String, default: T? = null, block: (String) -> T = { it as T }): Environment<T> =
    Environment(key, default, block)