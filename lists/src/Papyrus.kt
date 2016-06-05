abstract class Papyrus<T>: Comparable<Papyrus<T>>, Iterable<T> {
	abstract fun add(item: T)
	abstract fun get(index: Int): T
	abstract fun size(): Int
}