import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream

class InputTestStream(val str: String): InputStream(){
    var index = 0;
    override fun read(): Int {
        if(index < str.length) {
            index++;
            return str[index-1].toInt();
        } else
            return -1;
    }

}

class PrintTestStream(): PrintStream(EmptyStream()){
    val sb = StringBuilder();
    override fun print(c: Char) {
        sb.append(c);
    }
}

class EmptyStream : OutputStream() {
    override fun write(b: Int) {
    }

}
