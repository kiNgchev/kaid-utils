@file:Suppress("UNCHECKED_CAST")

package net.kingchev.kaid.resource

import kotlin.reflect.KProperty

public expect class Environment<T> {
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
}

public expect fun <T> env(key: String, default: T? = null, block: (String) -> T = { it as T}): Environment<T>