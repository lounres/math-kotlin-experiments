import java.util.LinkedList

data class Vertex(
    val x: Double,
    val y: Double
)

data class Edge(
    val firstVertex: Vertex,
    val secondVertex: Vertex
) {
    val vertices: Set<Vertex> get() = setOf(firstVertex, secondVertex)
    override fun equals(other: Any?): Boolean =
        if (other !is Edge) false
        else firstVertex == other.firstVertex && secondVertex == other.secondVertex
                || secondVertex == other.firstVertex && firstVertex == other.secondVertex

    override fun hashCode(): Int = firstVertex.hashCode() + secondVertex.hashCode()
    override fun toString(): String = "Edge($firstVertex, $secondVertex)"
}

data class Triangle(
    val firstVertex: Vertex,
    val secondVertex: Vertex,
    val thirdVertex: Vertex
) {
    val vertices: Set<Vertex> get() = setOf(firstVertex, secondVertex)
    val edges: Set<Edge> get() = setOf(Edge(firstVertex, secondVertex))
    override fun equals(other: Any?): Boolean =
        if (other !is Triangle) false
        else firstVertex == other.firstVertex && secondVertex == other.secondVertex && thirdVertex == other.thirdVertex
                || secondVertex == other.firstVertex && firstVertex == other.secondVertex && thirdVertex == other.thirdVertex
                || secondVertex == other.firstVertex && thirdVertex == other.secondVertex && firstVertex == other.thirdVertex
                || thirdVertex == other.firstVertex && secondVertex == other.secondVertex && firstVertex == other.thirdVertex
                || thirdVertex == other.firstVertex && firstVertex == other.secondVertex && secondVertex == other.thirdVertex
                || firstVertex == other.firstVertex && thirdVertex == other.secondVertex && secondVertex == other.thirdVertex
    override fun hashCode(): Int = firstVertex.hashCode() + secondVertex.hashCode() + thirdVertex.hashCode()
    override fun toString(): String = "Edge($firstVertex, $secondVertex, $thirdVertex)"
}

fun delaunayTriangulation(vertices: List<Vertex>) {
    if (vertices.size < 3) return

    val sortedVertices = vertices.sortedWith(compareBy({ it.x }, { it.y }))
    val vertexIterator = sortedVertices.iterator()
    val convexHull = LinkedList<Vertex>()
    val convexHullIterator = convexHull.listIterator()
    repeat(3) { convexHullIterator.add(vertexIterator.next()) }

}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}