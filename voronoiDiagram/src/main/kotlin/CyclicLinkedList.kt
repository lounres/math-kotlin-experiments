import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class CyclicLinkedList<E> {

    private class Node<E> {
        var item: E
        var next: Node<E>
        var prev: Node<E>
        constructor(prev: Node<E>, item: E, next: Node<E>) {
            this.prev = prev
            this.item = item
            this.next = next
        }
        constructor(item: E) {
            this.prev = this
            this.item = item
            this.next = this
        }
    }

    var size: Int = 0
        private set
    private var first: Node<E>? = null

    private fun linkAfter(element: E, node: Node<E>): Node<E> {
        val nextNode = node.next
        val newNode = Node(node, element, nextNode)
        node.next = newNode
        nextNode.prev = newNode
        size++
        return newNode
    }

    private fun linkBefore(element: E, node: Node<E>): Node<E> {
        val prevNode = node.prev
        val newNode = Node(prevNode, element, node)
        node.prev = newNode
        prevNode.next = newNode
        size++
        return newNode
    }

    private fun linkAfterMoving(element: E, node: Node<E>): Node<E> {
        val nextNode = node.next
        val newNode = Node(node, element, nextNode)
        node.next = newNode
        nextNode.prev = newNode
        if (node == first) first = newNode
        size++
        return newNode
    }

    private fun linkBeforeMoving(element: E, node: Node<E>): Node<E> {
        val prevNode = node.prev
        val newNode = Node(prevNode, element, node)
        node.prev = newNode
        prevNode.next = newNode
        if (node == first) first = newNode
        size++
        return newNode
    }

    private fun linkAfter(elements: Collection<E>, node: Node<E>) {
        if (elements.isEmpty()) return
        size += elements.size
        check(size >= 0)
        val elementIterator = elements.iterator()
        val nextNode = node.next
        var newNode = Node(node, elementIterator.next(), nextNode)
        node.next = newNode
        for (element in elementIterator) {
            val oldNode = newNode
            newNode = Node(oldNode, element, nextNode)
            oldNode.next = newNode
        }
        nextNode.prev = newNode
    }

    private fun linkBefore(elements: Collection<E>, node: Node<E>) {
        if (elements.isEmpty()) return
        size += elements.size
        check(size >= 0)
        val elementIterator = elements.iterator()
        val prevNode = node.prev
        var newNode = Node(prevNode, elementIterator.next(), node)
        prevNode.next = newNode
        for (element in elementIterator) {
            val oldNode = newNode
            newNode = Node(oldNode, element, node)
            oldNode.next = newNode
        }
        node.prev = newNode
    }

    private fun linkAfterMoving(elements: Collection<E>, node: Node<E>) {
        if (elements.isEmpty()) return
        size += elements.size
        check(size >= 0)
        val elementIterator = elements.iterator()
        val nextNode = node.next
        var newNode = Node(node, elementIterator.next(), nextNode)
        node.next = newNode
        for (element in elementIterator) {
            val oldNode = newNode
            newNode = Node(oldNode, element, nextNode)
            oldNode.next = newNode
        }
        nextNode.prev = newNode
        if (node === first) first = newNode
    }

    private fun linkBeforeMoving(elements: Collection<E>, node: Node<E>) {
        if (elements.isEmpty()) return
        size += elements.size
        check(size >= 0)
        val elementIterator = elements.iterator()
        val prevNode = node.prev
        var newNode = Node(prevNode, elementIterator.next(), node)
        prevNode.next = newNode
        if (node === first) first = newNode
        for (element in elementIterator) {
            val oldNode = newNode
            newNode = Node(oldNode, element, node)
            oldNode.next = newNode
        }
        node.prev = newNode
    }

    private fun fillWith(element: E): Node<E> {
        size = 1
        return Node(element).also { first = it }
    }

    private fun fillWith(elements: Collection<E>) {
        val elementIterator = elements.iterator()
        val firstNode = Node(elementIterator.next())
        var newNode = firstNode
        for (element in elementIterator) {
            val oldNode = newNode
            newNode = Node(oldNode, element, firstNode)
            oldNode.next = newNode
        }
        firstNode.prev = newNode
        size = elements.size
        first = firstNode
    }

    private fun unlink(node: Node<E>) : E {
        node.next.prev = node.prev
        node.prev.next = node.next
        if (first === node) first = node.next
        if (first === node) first = null
        size -= 1
        return node.item
    }

    private fun nodeAt(index: Int): Node<E> {
        var node = first ?: error("")
        when {
            index == 0 || index == size -> {}
            index <= size shr 1 -> repeat(index) { node = node.next }
            else -> repeat(size - index) { node = node.prev }
        }
        return node
    }

    fun clear() {
        val firstNode = first ?: return
        var toUnlink = firstNode
        do {
            val next = toUnlink.next
            toUnlink.next = toUnlink
            toUnlink.prev = toUnlink
            toUnlink = next
        } while (toUnlink !== firstNode)
        size = 0
        first = null
    }


    fun addAll(elements: Collection<E>): Boolean = addAll(size, elements)

    fun addAll(index: Int, elements: Collection<E>): Boolean {
        if (elements.isEmpty()) return false
        require(index in 0 .. size)
        when {
            size == 0 -> fillWith(elements)
            index == 0 -> linkBeforeMoving(elements, first!!)
            else -> linkBefore(elements, nodeAt(index))
        }
        return true
    }

    fun add(index: Int, element: E) {
        require(index in 0 .. size)
        when {
            size == 0 -> fillWith(element)
            index == 0 -> linkBeforeMoving(element, nodeAt(index))
            else -> linkBefore(element, nodeAt(index))
        }
    }

    fun add(element: E): Boolean {
        add(size, element)
        return true
    }

    fun get(index: Int): E = nodeAt(index).item

    fun isEmpty(): Boolean = size == 0

    fun iterator(): MutableIterator<E> = object : MutableIterator<E> {
        var currentNode: Node<E>? = first
        var previousNode: Node<E>? = null
        override fun hasNext(): Boolean = currentNode != null
        override fun next(): E {
            val node = currentNode
            require(node != null)
            currentNode = node.next.takeIf { it !== first }
            previousNode = node
            return node.item
        }
        override fun remove() {
            val node = previousNode
            require(node != null)
            node
        }

    }

    inner class MutableListIteratorImpl private constructor(
        private var currentNode: Node<E>? = first
    ): MutableListIterator<E> {
        override fun add(element: E) {
            currentNode.let {
                if (it != null) linkBefore(element, it)
                else fillWith(element)
            }
        }

        override fun hasNext(): Boolean = size != 0
        override fun hasPrevious(): Boolean = size != 0

        override fun next(): E = with(currentNode) {
            require(this != null)
            item.also { currentNode = next }
        }

        override fun nextIndex(): Int {
            TODO("Not yet implemented")
        }

        override fun previous(): E {
            TODO("Not yet implemented")
        }

        override fun previousIndex(): Int {
            TODO("Not yet implemented")
        }

        override fun remove() {
            TODO("Not yet implemented")
        }

        override fun set(element: E) {
            TODO("Not yet implemented")
        }

    }

    fun listIterator(): MutableListIteratorImpl = MutableListIteratorImpl()

    fun listIterator(index: Int): MutableListIterator<E> = MutableListIteratorImpl(nodeAt(index))

    fun set(index: Int, element: E): E = with(nodeAt(index)) { item.also { item = element } }
}