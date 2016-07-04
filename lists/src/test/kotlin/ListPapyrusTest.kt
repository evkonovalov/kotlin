package test.kotlin


import org.junit.*
import ListPapyrus;


fun evaluatesExpression() {
    val list = ListPapyrus<Int>();
    list.add(1);
    Assert.assertEquals(1,list.get(0));
}

/**
 * Created by Pilon_000 on 28.06.2016.
 */
