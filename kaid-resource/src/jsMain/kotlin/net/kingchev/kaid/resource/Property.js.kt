package net.kingchev.kaid.resource

import kotlin.reflect.KProperty

private external val require: dynamic
private val fs: dynamic = require("fs")

public actual class Property<T>(
    private val key: String,
    private val default: T?,
    path: String = "application.properties",
    system: Boolean = false,
    private val block: (String) -> T
) {
    private val properties: MutableMap<String, String> = hashMapOf()

    private fun parsePath(path: String): String {
        if (path.isBlank() or path.isEmpty())
            throw IllegalArgumentException("Path is invalid")
        if (path.startsWith("/"))
            return path.removePrefix("/")
        return path
    }

    init {
        val path = parsePath(path)
        val content = fs.readFileSync("./kotlin/$path", encoding = "UTF-8").toString()
        val lines = content.split("\n")
        lines.forEach {
            if (it.isBlank() or it.isEmpty())
                return@forEach

            val arr = it.split("=")

            if (arr.size != 2)
                throw IllegalStateException("Properties is invalid")
            val (key, value) = arr
            properties[key] = value
        }
    }

    public actual operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val result = properties[key]
            ?: return default
            ?: throw NullPointerException("This property value is not defined for $key")

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

