import kotlin.test.assertEquals
import org.junit.Test
import java.io.File
import java.io.FileWriter
import java.io.Reader
import java.io.StringReader

class VmTest {

    val file = File("inputTest.txt");
    val fileWriter = FileWriter(file,false);
    var testReader = StringReader("");

    @Test fun helloworld(){ //Writes Hello World!
        var vm = Vm("inputTest.txt", testReader);
        fileWriter.write("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        fileWriter.close();
        vm.start();
        assertEquals("Hello World!\n", String(vm.str));
    }

    @Test fun div(){ //Divides six on two
        var vm = Vm("inputTest.txt", testReader);
        fileWriter.write("+>+++++++>++<[<+>>[<<->>->+>]>[<<+>>[<+>-]]<<<<[<]>>-]<-.");
        fileWriter.close();
        vm.start();
        assertEquals(3.toByte().toChar(), vm.str[0]);
    }

    @Test fun plus(){ //Sum 11 and 2
        var vm = Vm("inputTest.txt", testReader);
        fileWriter.write("+++++++++++>++[<+>-]<.");
        fileWriter.close();
        vm.start();
        assertEquals(13.toByte().toChar(), vm.str[0]);
    }

    @Test fun factorial(){ //5!
        var vm = Vm("inputTest.txt", testReader);
        fileWriter.write("+++++>>>>+<<<<[->+[->+>+<<]>[-<+>]>[->[->+>+<<]>[-<+>]<<]>[-]>>[-<<+>>]<<<<<<]>>>>.");
        fileWriter.close();
        vm.start();
        assertEquals(120.toByte().toChar(), vm.str[0]);
    }

    @Test fun simpleReading(){ //Reads number, prints char
        testReader = StringReader("124");
        var vm = Vm("inputTest.txt", testReader);
        fileWriter.write(",.");
        fileWriter.close();
        vm.start();
        assertEquals(124.toByte().toChar(), vm.str[0]);
    }

    @Test fun readerMult(){ //Reads two numbers and multiply them
        testReader = StringReader("12 32");
        var vm = Vm("inputTest.txt", testReader);
        fileWriter.write(",>,<[>[>+>+<<-]>[<+>-]<<-]>>>.");
        fileWriter.close();
        vm.start();
        assertEquals((12*32).toByte().toChar(), vm.str[0]);
    }
}