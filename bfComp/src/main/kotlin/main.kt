import com.sun.xml.internal.fastinfoset.util.StringArray
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.Opcodes
import jdk.internal.org.objectweb.asm.commons.LocalVariablesSorter
import jdk.internal.org.objectweb.asm.util.ASMifier
import java.io.File

class MyClassLoader :ClassLoader(){
    fun defineClass(name: String, b: ByteArray):Class<*> {
        return defineClass(name, b, 0, b.size);
    }

}
    fun main(args: Array<String>) {
        val cc = CommandCompiler();
        cc.compile("input");
        //ASMifier.main(Array<String>(1, { "SimpleClass" }));
        //k.getMethod("main").invoke(k);

    }
