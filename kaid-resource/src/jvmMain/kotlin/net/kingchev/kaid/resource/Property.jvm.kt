package net.kingchev.kaid.resource

import java.io.FileNotFoundException
import java.util.*
import kotlin.reflect.KProperty

public actual class Property<T>(
    private val key: String,
    private val default: T?,
    private var path: String = "application.properties",
    system: Boolean = false,
    private val block: (String) -> T
) {
    private val properties: Properties

    init {
        if (!system) {
            if (!path.startsWith("/"))
                path = "/$path"
            properties = Properties()
            properties.load(javaClass.getResourceAsStream(path)
                ?: throw FileNotFoundException("Properties file not found on path: $path")
            )
        } else properties = System.getProperties()
    }

    public actual operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val result = properties.getProperty(key)

        if (result == null && default == null)
            throw NullPointerException("This property value is not defined for $key")
        else if (result == null && default != null)
            return default

        return block(result)
    }

    public actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T): Nothing
            = throw UnsupportedOperationException("You may not change this value")
}



public actual fun <T> property(
    key: String,
    path: String,
    system: Boolean,
    default: T?,
    block: (String) -> T
): Property<T> =
    Property(key, default, path, system, block)

public actual fun <T> property(
    key: String,
    default: T?,
    system: Boolean,
    block: (String) -> T
): Property<T> =
    Property(key, default, system = system, block = block)

