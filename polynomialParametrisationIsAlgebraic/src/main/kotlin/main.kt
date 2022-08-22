import com.lounres.kone.algebraic.invoke
import com.lounres.kone.algebraic.ring
import com.lounres.kone.polynomial.*
import space.kscience.kmath.expressions.Symbol
import space.kscience.kmath.expressions.symbol

fun signatureComparator(variables: Set<Symbol>) : Comparator<Map<Symbol, UInt>> =
    compareBy(
        *variables
            .sortedBy { it.identity }
            .map { { sig : Map<Symbol, UInt> -> sig[it] } }
            .toTypedArray()
    )

val LabeledPolynomial<*>.variables: Set<Symbol>
    get() =
        buildSet {
            coefficients.entries.forEach { (degs) -> addAll(degs.keys) }
        }

@OptIn(DelicatePolynomialAPI::class)
fun LabeledPolynomial<Int>.clear(): LabeledPolynomial<Int> =
    LabeledPolynomialWithoutCheck(coefficients.filterValues { it != 0 })

val LabeledPolynomial<*>.leadingTerm : Map<Symbol, UInt>
    get() = coefficients.keys.maxWith(signatureComparator(variables)) // TODO: Оптимизировать

fun polynomialLeadingTermComparator(variables: Set<Symbol>) : Comparator<LabeledPolynomial<*>> =
    compareBy(signatureComparator(variables), LabeledPolynomial<*>::leadingTerm)

fun testLinearIndependence(polynomials: List<LabeledPolynomial<Int>>) : Boolean = Int.ring.labeledPolynomialSpace {
    val polynomials = polynomials.toMutableList()
    val variables = polynomials.map { it.variables }.fold(emptySet<Symbol>()) { acc, symbols -> acc + symbols }
    val signatureComparator = signatureComparator(variables)

    fun <C> LabeledPolynomialSpace<C, *>.fixPolynomials(
        pol1: LabeledPolynomial<C>,
        coef1: C,
        pol2: LabeledPolynomial<C>,
        coef2: C
    ): LabeledPolynomial<C> = pol1 * coef2 - pol2 * coef1

    while (polynomials.isNotEmpty()) {
        if (polynomials.any { it.isZero() }) return false
        val (maxSignature, maxPolynomials) = polynomials.withIndex().groupBy { it.value.leadingTerm }.maxWith(compareBy(signatureComparator) { it.key })
        val (removeIndex, removePolynomial) = maxPolynomials[0]
        val removeCoefficient = removePolynomial.coefficients[maxSignature]!!
        for ((changeIndex, changePolynomial) in maxPolynomials.drop(1)) {
            val changeCoefficient = changePolynomial.coefficients[maxSignature]!!
            polynomials[changeIndex] = fixPolynomials(changePolynomial, changeCoefficient, removePolynomial, removeCoefficient).clear()
        }
        polynomials.removeAt(removeIndex)
    }

    return true
}

val x by symbol
val y by symbol
val z by symbol

fun main() {
    Int.ring.labeledPolynomialSpace {
        println(testLinearIndependence(listOf(
            LabeledPolynomial(
                mapOf(x to 1u) to 1,
                mapOf(y to 1u) to 2,
            ),
            LabeledPolynomial(
                mapOf(x to 1u) to 2,
                mapOf(z to 1u) to 4,
            ),
            LabeledPolynomial(
                mapOf(y to 1u) to -15,
                mapOf(z to 1u) to 15,
            ),
        )))
    }
}