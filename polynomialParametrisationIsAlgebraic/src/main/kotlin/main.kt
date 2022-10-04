import com.lounres.kone.algebraic.RationalField
import com.lounres.kone.algebraic.Ring
import com.lounres.kone.algebraic.invoke
import com.lounres.kone.linearAlgebra.Matrix
import com.lounres.kone.linearAlgebra.MatrixSpace
import com.lounres.kone.polynomial.LabeledPolynomial
import com.lounres.kone.polynomial.LabeledPolynomialSpace
import space.kscience.kmath.expressions.Symbol


typealias Descriptor = List<Int>

data class DescriptorUpgrade(val descriptor: Descriptor, val index: Int)

val Descriptor.nextDescriptors: List<DescriptorUpgrade> get() = List(size) { DescriptorUpgrade(toMutableList().apply { this[it]++ }, it) }

data class Signature<T>(
    val descriptor: Descriptor,
    val polynomial: LabeledPolynomial<T>,
)

typealias SignatureCache<T> = Collection<Signature<T>>
data class IndexedSignatureCache<C>(val index: Int, val signatureCache: SignatureCache<C>)

class IndexedSignatureCachesGenerator<C>(
    k: Int,
    val context: LabeledPolynomialSpace<C, *>,
    val Ps: List<LabeledPolynomial<C>>
): Iterator<IndexedSignatureCache<C>> {
    private var cache: SignatureCache<C> = setOf(Signature(
        descriptor = List(k) { 0 },
        polynomial = context.one
    ))
    private var index: Int = 0

    override fun hasNext(): Boolean = true

    override fun next(): IndexedSignatureCache<C> {
        val newCache: MutableMap<Descriptor, Signature<C>> = mutableMapOf()

        for (signature in cache)
            for ((nextDescriptor, index) in signature.descriptor.nextDescriptors)
                if (nextDescriptor !in newCache)
                    newCache[nextDescriptor] = Signature(
                        descriptor = nextDescriptor,
                        polynomial = context { signature.polynomial * Ps[index] }
                    )

        cache = newCache.values
        index++
        return IndexedSignatureCache(index = index, signatureCache = cache)
    }
}

fun descriptorList(size: Int, deg: Int): List<Descriptor> = sequence {
    val now = MutableList(size) { 0 }
    now[0] = deg
    while (true) {
        yield(now.toList())
        val updateIndex = now.subList(0, size-1).indexOfLast { it != 0 }
        if (updateIndex == -1) break
        val endValue = now.last()
        now[size - 1] = 0
        now[updateIndex]--
        now[updateIndex + 1] = endValue + 1
    }
}.toList()

fun coefficientNamer(k: Int, monomial: Descriptor) = "c_${k}_${monomial.joinToString(separator = ",")}"
fun Symbol(k: Int, monomial: Descriptor) = Symbol(coefficientNamer(k, monomial))

fun <C> SignatureCache<C>.isIndependent(context: MatrixSpace<C, Ring<C>>, k: Int, monomials: List<Descriptor>, deg: Int): Boolean = context {
    val descriptorsList = descriptorList(k * monomials.size, deg)
    val matrixList = map { (_, polynomial) ->
        descriptorsList.map {
            val polynomialSignature = buildMap(k * monomials.size) {
                for (curK in 0 until k) for ((id, curMonomial) in monomials.withIndex())
                    it[id * k + curK].let { if (it > 0) put(Symbol(curK, curMonomial), it.toUInt()) }
            }
            polynomial.coefficients[polynomialSignature] ?: context.ring.zero
        }
    }

    context { Matrix(matrixList).rank } == size
}

fun D(k: Int, n: Int, d: Int): Int {
    val monomials = descriptorList(n, d)
    val field = RationalField
    val coefficientsRing = LabeledPolynomialSpace(RationalField)
    val Ps = List(k) {
        LabeledPolynomial(buildMap {
            for (monomial in monomials)
        })
    }
    for ((signature, index) in IndexedSignatureCachesGenerator(k, LabeledPolynomialSpace(coefficientsRing), Ps))
}

fun main() {
}