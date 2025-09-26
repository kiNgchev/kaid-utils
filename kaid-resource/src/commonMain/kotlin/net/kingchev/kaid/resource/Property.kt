@file:Suppress("UNCHECKED_CAST")

package net.kingchev.kaid.resource

import kotlin.reflect.KProperty

public expect class Property<T> {
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
}

public expect fun <T> property(
    key: String,
    path: String,
    system: Boolean = false,
    default: T? = null,
    block: (String) -> T = { it as T }
): Property<T>

public expect fun <T> property(
    key: String,
    default: T? = null,
    system: Boolean = false,
    block: (String) -> T = { it as T }
): Property<T>

