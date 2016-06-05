import kotlin.collections.List

fun main(args: Array<String>) {
    val a: ArrayPapyrus<Int> = ArrayPapyrus<Int>();
    for(i in 1..5){
        val d = readLine()?.toInt() as Int;
        a.add(d);
    }
    for(item in a) {
        println("$item");
    }
}