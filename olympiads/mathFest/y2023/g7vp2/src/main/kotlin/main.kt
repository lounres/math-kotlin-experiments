/*
 * Copyright © 2023 Gleb Minaev
 * All rights reserved. Licensed under the Apache License, Version 2.0. See the license in file LICENSE
 */

@file:Suppress("ClassName", "NonAsciiCharacters", "PropertyName", "LocalVariableName", "ObjectPropertyName")

import com.lounres.kone.algebraic.Rational
import com.lounres.kone.algebraic.invoke
import com.lounres.kone.combinatorics.enumerative.permutations
import com.lounres.kone.util.collectionOperations.component6
import com.lounres.kone.util.collectionOperations.component7

/**
 * The data class is going to store solutions of the rebus.
 *
 * P.S. I called it and variables in it to not confuse myself later.
 */
data class ФЕВРАЛЬ(
    val Ф: Int,
    val Е: Int,
    val В: Int,
    val Р: Int,
    val А: Int,
    val Л: Int,
    val Ь: Int,
)

/**
 * Plain fabric that just uses the first 7 elements to construct the possible solution.
 */
fun ФЕВРАЛЬ(args: List<Int>): ФЕВРАЛЬ {
    val (Ф, Е, В, Р, А, Л, Ь) = args
    return ФЕВРАЛЬ(
        Ф = Ф,
        Е = Е,
        В = В,
        Р = Р,
        А = А,
        Л = Л,
        Ь = Ь,
    )
}

/**
 * Property represents the first fraction in the rebus.
 */
val ФЕВРАЛЬ.frac1: Rational get() = Rational(Ф, Е)
/**
 * Property represents the second fraction in the rebus.
 */
val ФЕВРАЛЬ.frac2: Rational get() = Rational(В * 10 + Р, (А * 10 + Л) * 10 + Ь)

/**
 * Just a plain list of digits.
 */
val digits = (0 until 10).toList()

/**
 * Function that validates that the sum is actually one.
 * So, if the digits are all different, the instance is the sought solution of the rebus.
 */
fun ФЕВРАЛЬ.check(): Boolean = Rational.field {
    frac1 + frac2 eq one
}

/**
 * A list of all the rebus solutions.
 */
val февральCombinations: Sequence<ФЕВРАЛЬ> =
    digits.permutations(7) // We generate all 7-permutations of digits list, so we get all possible combinations of non-equal digits.
        .map { ФЕВРАЛЬ(it) } // We put them in our data class.
        .filter { it.Е != 0 && it.А != 0 && it.check() } // Then we filter out all non-solutions.
        .sortedWith( // In the end, we sort the solutions. I was also asked to sort them.
            Comparator<ФЕВРАЛЬ> { r1, r2 -> Rational.field { (r1.frac1 - r2.frac1).numerator compareTo 0 } }.thenBy { it.Ф }
        )

fun main() {
    // So here we are printing all the rebus solutions in three different formats.
    println("Ф Е В Р А Л Ь | ФЕВРАЛЬ | Ф/Е+ВР/АЛЬ")
    for (февраль in февральCombinations) println(февраль.run { "$Ф $Е $В $Р $А $Л $Ь | $Ф$Е$В$Р$А$Л$Ь | $Ф/$Е+$В$Р/$А$Л$Ь" })
}