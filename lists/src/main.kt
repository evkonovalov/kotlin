import kotlin.collections.List

fun main(args: Array<String>) {
    val a: ArrayPapyrus<Int> = ArrayPapyrus<Int>();
    val b: ListPapyrus<Int> = ListPapyrus<Int>();
    for(i in 1..5){
        val d = readLine()?.toInt() as Int;
        a.add(d);
    }
    for(i in 1..5){
        val d = readLine()?.toInt() as Int;
        b.add(d);
    }
  /*  for(item in a) {
        println("$item");
    }
    println();
    for(item in b) {
        println("$item");
    }
    println(a.equals(b)); */ //Works

    println(a.get(3).toString().plus(" ").plus(b.get(4).toString())); 
}
