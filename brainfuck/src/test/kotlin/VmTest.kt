import kotlin.test.assertEquals
import org.junit.Test
import java.nio.charset.Charset
import java.io.File

class VmTest {

    @Test fun helloworld(){ //Writes Hello World!
        var vm = Vm();
        vm.reader = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals("Hello World!", String(vm.str));
    }

    @Test fun div(){ //Divides six on two
        var vm = Vm();
        vm.reader = "+>+++++++>++<[<+>>[<<->>->+>]>[<<+>>[<+>-]]<<<<[<]>>-]<-.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals(3.toByte().toChar(), vm.str[0]);
    }

    @Test fun plus(){ //Sum 11 and 2
        var vm = Vm();
        vm.reader = "+++++++++++>++[<+>-]<.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals(13.toByte().toChar(), vm.str[0]);
    }

    @Test fun factorial(){ //5!
        var vm = Vm();
        vm.reader = "+++++>>>>+<<<<[->+[->+>+<<]>[-<+>]>[->[->+>+<<]>[-<+>]<<]>[-]>>[-<<+>>]<<<<<<]>>>>.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals(120.toByte().toChar(), vm.str[0]);
    }
}