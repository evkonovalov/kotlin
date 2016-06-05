class ArrayList<T>() : List<T>() {
    private val data : Array<T>;
    private var size: Int = 0;
	init {
        data =  Array<Any?>(100, { i -> null }) as Array<T>;
    }

	override fun iterator(): Iterator<T> { //не работает
        class ArrayListIterator<T> : Iterator<T> {
            override fun hasNext(): Boolean {
                return !(data.iterator().next() == null)
            }

            override fun next(): T {
                return data.iterator().next() as T;
            }
        }
        return ArrayListIterator<T>();
	}

	override fun toString(): String {
        val s: String = String();
		for(item in data) {
            s.plus(item.toString());
        }
        return s;
	}
	
	override fun hashCode(): Int {
        val s: String = String();
        for(item in data) {
            s.plus(item?.hashCode().toString());
        }
        return s.hashCode();
	}
	
	override fun equals(other: Any?): Boolean{
        return other?.hashCode() == this.hashCode()
	}

	override fun compareTo(other: List<T>): Int {
		if(equals(other))
            return 0;
        else
            return -1;
	}
	
	override fun add(item: T) {
        data[size] = item;
        size++;
	}
	
	override fun get(index: Int): T {
		return data[index];
	}
	
	override fun size(): Int {
		return size;
	}
}