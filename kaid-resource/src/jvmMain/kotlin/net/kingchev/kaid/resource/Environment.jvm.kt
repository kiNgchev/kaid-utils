package net.kingchev.kaid.resource

import kotlin.reflect.KProperty

public actual class Environment<T>(
    private val key: String,
    private val default: T?,
    private val block: (String) -> T
) {
    public actual operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val result = System.getenv(key)
            ?: return default
            ?: throw NullPointerException("This environment value is not defined for $key")

        return block(result)
    }

    public actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
            = throw UnsupportedOperationException("You may not change this value")
}

public actual fun <T> env(key: String, default: T?, block: (String) -> T): Environment<T> =
    Environment(key, default, block)