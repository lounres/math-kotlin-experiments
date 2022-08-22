fun arcCompute(seqList: List<List<Int>>, computed: HashMap<List<Int>, Int>) : HashMap<List<Int>, Int> {

    val seqList = seqList.map { it.filter { it != 0 } }

    fun update(seq: List<Int>) : Int {
        val seq = seq.filter { it != 0 }
        if (seq !in computed) computed[seq] = -1
        if (computed[seq]!! < 0)
            computed[seq] =
                when {
                    seq.isEmpty() -> 1
                    seq.size == 1 -> 0
                    seq.sum() % 2 == 1 -> 0
                    else ->
                        (1 until seq.size).sumOf { k ->
                            //for l in range(seq[k])
                            (0 until seq[k]).sumOf { l ->
                                update(seq.subList(1, k) + listOf(l)) * update(
                                    listOf(
                                        seq[0] - 1,
                                        seq[k] - l - 1
                                    ) + seq.subList(k + 1, seq.size)
                                )
                            }
                        }
                }

        return computed[seq]!!
    }

    seqList.map { update(it) }

    return computed
}

/**
 *   This function computing count of variants to connect vertexes on line by non-crossing edges in a half-plane bordered
 *   by that line.
 *
 *   @param seq sequence of degrees (they are integers) of vertexes
 *   @return count of variants
 */
fun arc(seq: List<Int>) : Int = arcCompute(listOf(seq), HashMap())[seq.filter { it != 0 }]!!

fun arc(vararg seq: Int) = arc(seq.toList())


/**
 *   This function computing count of variants to connect vertexes on line by non-crossing edges in several half-plane
 *   bordered by that line.
 *
 *   :param count_of_planes:
 *   :param seq:
 *   :return:
 */
fun arc_n_planes(count_of_planes: Int, seq: List<Int>) : Int { // TODO: Какая-то фигня. Переделать.

    val computed = HashMap<List<Int>, Int>()

    if (count_of_planes == 1) return arc(seq)

    val thatHalfPlane = MutableList(seq.size) { 0 }
    var index: Int

    fun findNchange () {
        index = 0
        while (index < seq.size) {
            if (thatHalfPlane[index] < seq[index]) {
                thatHalfPlane[index] += 1
                for (i in 0 until index) thatHalfPlane[i] = 0
                break
            }
            index += 1
        }
    }

    var res = arc_n_planes(count_of_planes - 1, seq)
    index = -1
    while (thatHalfPlane != seq) {
        findNchange()
        arcCompute(listOf(thatHalfPlane), computed = computed)
        res += computed[thatHalfPlane.filter { it != 0 }]!! *
                arc_n_planes(
                    count_of_planes - 1,
                    seq.mapIndexed { ind, i -> i - thatHalfPlane[ind] }
                )
    }
    return res
}
