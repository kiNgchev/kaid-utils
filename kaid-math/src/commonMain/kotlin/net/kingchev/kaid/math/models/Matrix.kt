package net.kingchev.kaid.math.models

public data class Matrix(val columns: Byte, val rows: Array<Array<Number>>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Matrix

        return columns == other.columns
    }
}

public fun matrix(rows: Array<Array<Number>>): Matrix {
    var previous: Array<Number>? = null
    for (row in rows) {
        if (previous == null) {
            previous = row
            continue
        }

        if (row.size != previous.size) {
            throw Throwable("Invalid matrix")
        }
    }
    return Matrix(rows[0].size.toByte(), rows)
}