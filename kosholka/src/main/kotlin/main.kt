val n = 4
var stepCounter = 0

fun BooleanArray.log() {
    println(joinToString(separator = "") { if (it) "1" else "0" })
    stepCounter++
}

inline fun BooleanArray.indexOfLast(from: Int, predicate: (Boolean) -> Boolean): Int {
    for (index in (0..from).reversed()) {
        if (predicate(this[index])) {
            return index
        }
    }
    return -1
}

fun main() {
    val state = BooleanArray(n + 1) { it == 0 }
    state.log()

    fun switch(index: Int) {
        require(index in 1..n && state[index - 1])
        state[index] = !state[index]
        state.log()
    }

    fun change(index: Int) {
        require(index in 1..n)
        val l = state.indexOfLast(index - 1) { it }
        if (l == index - 1) switch(index)
        else {
            change(l + 1)
            if (l > 0) change(l)
            change(index)
            if (l > 0) change(l)
            change(l+1)
        }
    }

    change(n)
    println(stepCounter)
}