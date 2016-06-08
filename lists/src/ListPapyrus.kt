
class ListPapyrus<T> :Papyrus<T>() {

    class ListPapyrusItem<T>(newData: T){
        val data = newData;
        var nextItem: ListPapyrusItem<T>?;

        init {
            nextItem = null;
        }
    }
    var elementNow = 0;
    var size = 0;
    var head: ListPapyrusItem<T>?;
    init {
        head = ListPapyrusItem<T>(null as T);
    }

    override fun iterator(): Iterator<T> {
        class ArrayListIterator<T> : Iterator<T> {
            override fun hasNext(): Boolean {
                val ret =  (elementNow < size);
                if(elementNow >= size)
                    elementNow = 0;
                return ret;
            }

            override fun next(): T {
                var curr = head;
                for(i in 1..elementNow)
                    curr = curr?.nextItem;
                elementNow++;
                return curr?.data as T;
            }
        }
        return ArrayListIterator<T>();
    }

    override fun toString(): String {
        val s: String = String();
        for(item in this) {
            s.plus(item.toString());
        }
        return s;
    }

    override fun hashCode(): Int {
        val s: String = String();
        for(item in this) {
            s.plus(item?.hashCode().toString());
        }
        return s.hashCode();
    }

    override fun equals(other: Any?): Boolean{
        return other?.hashCode() == this.hashCode()
    }

    override fun compareTo(other: Papyrus<T>): Int {
        if(equals(other))
            return 0;
        else
            return -1;
    }

    override fun add(item: T) {
        if (size == 0)
            head = ListPapyrusItem<T>(item);
        else {
            var curr = head;
            while(curr?.nextItem != null)
                curr = curr?.nextItem;
            curr?.nextItem = ListPapyrusItem<T>(item);
        }
        size++;
    }

    override fun get(index: Int): T {
        if (index >= size) {
            error("ListPapyrus: index more then size")
            return null as T;
        }
        else {
            var curr = head;
            for(i in 1..index)
                curr = curr?.nextItem;
            return curr?.data as T;
        }
    }

    override fun size(): Int {
        return size;
    }
}