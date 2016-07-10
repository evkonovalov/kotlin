import org.junit.Test
import java.io.*
import kotlin.test.assertEquals

class CommandCompilerTest {
    val compiler = CommandCompiler();
    val fileWriter = FileWriter(File("TestClass.txt"), false);

    @Test fun helloworld(){ //Writes Hello World!\n
        testCompiler("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.",
                "","Hello World!\n");
    }

    @Test fun div(){ //Divides six on two
        testCompiler("+>+++++++>++<[<+>>[<<->>->+>]>[<<+>>[<+>-]]<<<<[<]>>-]<-.", "",3.toChar().toString());
    }

    @Test fun plus(){ //Sum 11 and 2
        testCompiler("+++++++++++>++[<+>-]<.", "",13.toChar().toString());
    }

    @Test fun factorial(){ //5!
        testCompiler("+++++>>>>+<<<<[->+[->+>+<<]>[-<+>]>[->[->+>+<<]>[-<+>]<<]>[-]>>[-<<+>>]<<<<<<]>>>>.", "",120.toChar().toString());
    }

    @Test fun simpleReading(){ //Reads number, prints char
        testCompiler(",.", "124",124.toChar().toString());
    }

    @Test fun readerMult(){ //Reads two numbers and multiply them
        testCompiler(",>,<[>[>+>+<<-]>[<+>-]<<-]>>>.", "12 32",(12 * 32).toChar().toString());
    }

    fun testCompiler(program : String, input : String, output : String) {
        fileWriter.write(program);
        fileWriter.close();
        compiler.compile("TestClass");
        val classByteArray = FileInputStream("TestClass.class").readBytes();
        val out = PrintTestStream();
        System.setIn(InputTestStream(input));
        System.setOut(out);

        val exprClass = ByteArrayClassLoader().loadClass("TestClass", classByteArray);
        val methods = exprClass?.methods;
        if (methods == null || methods.isEmpty()) {
            throw AssertionError("No main method was found.");
        }
        for (method in methods) {
            if (method.name != "main") {
                continue;
            }
            method.invoke(null, arrayOf<String>());
            assertEquals(output, out.sb.toString());
            return;
        }
    }

    class ByteArrayClassLoader() : ClassLoader() {
        fun loadClass(name : String?, buf : ByteArray) : Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }
}