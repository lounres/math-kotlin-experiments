/*
 * Copyright © 2023 Gleb Minaev
 * All rights reserved. Licensed under the Apache License, Version 2.0. See the license in file LICENSE
 */

import com.lounres.kone.algebraic.Rational
import com.lounres.kone.algebraic.invoke
import com.lounres.kone.misc.planimetricsCalculation.*


fun main(): Unit = Rational.field.planimetricsCalculationContext {
    /*
    Let H be an orthocenter of triangle △ABC.
    Tangent to circle ω(BHC) at H intersects lines (AB) and (AC) at P and Q respectively.
    Circle ω(APQ) intersect circle ω(ABC) again at K.
    Tangents to ω(APQ) at A and K intersect at T.
    Prove that T, H, and midpoint M of segment [AB] are collinear.
     */

    // Let's declare point of our triangle.
    // We assign concrete points to A and B because
    // with loss of generality we lessen computer's work.
    val A = origin
    val B = xBasis
    val C by Point

    // Then we calculate the orthocenter and the tangent at it.
    val H = orthocenter(A, B, C)
    val h = H.polarBy(circumcircle(B, C, H))

    // Then we intersect it with the edges to get P and Q.
    val P = intersectionOf(h, lineThrough(A, B))
    val Q = intersectionOf(h, lineThrough(A, C))

    // Then we calculate T. But we use a different approach for it.
    // We calculate line (OK) by constructing perpendicular to
    // (ABC) and (APQ) centers line. Then T is its polar by (APQ).
    val l = perpendicular(lineThrough(circumcenter(A, B, C), circumcenter(A, P, Q)), A)
    val T = l.poleBy(circumcircle(A, P, Q))

    // Then we compute the midpoint M.
    val M = midpoint(B, C)

    // Finally, we test if the fact is really true.
    println(collinearityTest(T, H, M))
}