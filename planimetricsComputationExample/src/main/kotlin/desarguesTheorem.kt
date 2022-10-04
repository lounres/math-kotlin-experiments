@file:Suppress("LocalVariableName")

import com.lounres.kone.algebraic.Rational
import com.lounres.kone.algebraic.invoke
import com.lounres.kone.misc.planimetricsCalculation.*

fun main() = Rational.field.planimetricsCalculationContext {
    // Let's discuss Desargues's theorem.

    // Let A1, B1, C1, A2, B2, and C2 be points on plane.
    val A1 = Point("A1")
    val B1 = Point("B1")
    val C1 = Point("C1")
    val A2 = Point("A2")
    val B2 = Point("B2")
    val C2 = Point("C2")

    // Let a, b, and c be lines through A1 and A2, B1 and B2, C1 and C2 respectively.
    val a = lineThrough(A1, A2)
    val b = lineThrough(B1, B2)
    val c = lineThrough(C1, C2)

    // Also, let A`, B`, and C` be intersection points of lines
    // (B1 C1) and (B2 C2), (C1 A1) and (C2 A2), (A1 B1) and (A2 C2) respectively.
    val Aprime = intersectionOf(lineThrough(B1, C1), lineThrough(B2, C2))
    val Bprime = intersectionOf(lineThrough(C1, A1), lineThrough(C2, A2))
    val Cprime = intersectionOf(lineThrough(A1, B1), lineThrough(A2, B2))

    // The theorem states that lines a, b, and c are concurrent iff A`, B`, and C` are collinear. Let's prove this.

    // Both conditions are polynomial conditions on initial points A1, B1, C1, A2, B2, and C2:
    val desarguesTheoremConcurrencyCondition = concurrencyCondition(a, b, c)
    val desarguesTheoremCollinearityCondition = collinearityCondition(Aprime, Bprime, Cprime)
    // Uncomment the following lines to print the polynomials.
//    println(desarguesTheoremConcurrencyCondition)
//    println(desarguesTheoremCollinearityCondition)
//    println()
    // But be aware that they are very big.

    // Let's check such equality:
    println(polynomialSpace {
        desarguesTheoremConcurrencyCondition *
                collinearityCondition(A1, B1, C1) *
                collinearityCondition(A2, B2, C2) eq desarguesTheoremCollinearityCondition
    })
    println()
    // >>> true

    // This equality means that A`, B`, and C` are collinear iff either a, b, and c are concurrent,
    // A1, B1, and C1 are collinear or A2, B2, and C2 are collinear.

    // Well, the theorem supposes that the points are distinct. Thus, we proved the theorem in a computational way!
    // Congratulations to us!!!
}