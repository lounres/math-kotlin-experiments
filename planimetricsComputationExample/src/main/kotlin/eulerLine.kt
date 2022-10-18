@file:Suppress("LocalVariableName")

import com.lounres.kone.algebraic.invoke
import com.lounres.kone.algebraic.Rational
import com.lounres.kone.misc.planimetricsCalculation.*


fun main() = eulerLine()
fun eulerLine() = Rational.field.planimetricsCalculationContext {
    // Let A, B, and C by points (on plane):
    val A by Point
    val B by Point
    val C by Point
    // The points form a triangle.

    // Let M, H, and O be centroid, orthocenter, and circumcenter of the triangle:
    val M = centroid(A, B, C)
    val H = orthocenter(A, B, C)
    val O = circumcenter(A, B, C)

    // Let's check that the 3 points lie on one line:
    println(polynomialSpace { collinearityCondition(M, H, O).isZero() })
    println()
    // >>> true
    // It means they do.

    // This actually must be true because of the Euler line existence:
    // https://en.wikipedia.org/wiki/Euler_line

    // Well, there is also predefined Euler line:
    val l = eulerLine(A, B, C)

    // Let's check that this is true Euler line:
    for (P in listOf(M, H, O)) println(P isLyingOn l)
    println()
    // >>> true
    // >>> true
    // >>> true

    // We can also check this with obvious equality check:
    println(l equalsTo lineThrough(O, H))
    println()
    // >>> true

    // But be aware of what you do and read documentation properly.
    // Equality of two lines does not mean that their corresponding coordinates are equal.
    // It means they are proportional.
    polynomialSpace {
        println(l.z.degree)
        println(lineThrough(O, H).z.degree)
        println()
        // >>> 9
        // >>> 12
    }
}