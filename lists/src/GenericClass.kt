class GenericClass<T>() {
    val arr : Array<T?>

    init {
        arr = Array<Any?>(10, { null }) as Array<T?>
        var x = 5 // `Int` type is inferred
        x += 1
    }
}