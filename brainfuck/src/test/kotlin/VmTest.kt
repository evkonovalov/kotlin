import kotlin.test.assertEquals
import org.junit.Test
import java.nio.charset.Charset
import java.io.File

class VmTest {

    @Test fun helloworld(){
        var vm = Vm();
        vm.reader = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals("Hello World!", String(vm.str));
    }

    @Test fun div(){
        var vm = Vm();
        vm.reader = "+>+++++++>++<[<+>>[<<->>->+>]>[<<+>>[<+>-]]<<<<[<]>>-]<-.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals(3.toByte().toChar(), vm.str[0]);
    }

    @Test fun plus(){
        var vm = Vm();
        vm.reader = "+++++++++++>++[<+>-]<.".toByteArray(Charset.defaultCharset());
        vm.start();
        assertEquals(13.toByte().toChar(), vm.str[0]);
    }
}