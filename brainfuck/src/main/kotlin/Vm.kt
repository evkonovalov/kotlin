import java.io.File
class Vm {
    private var bytes: ByteArray = ByteArray(30000,{i -> 0});
    private var index = 0;
    private var rIndex = 0;
    var reader = File("input.txt").readBytes();
    var str = CharArray(30);
    var charIndex = 0;

    fun indInc(){
        if (index + 1 >= 30000)
            index = 0;
        else
            index++;
    }

    fun indDec(){
        if (index - 1 < 0)
            index = 29999;
        else
            index--;
    }

    fun startCycle(){
        if(bytes[index] == 0.toByte()) {
            var brackets = 1;
            while(brackets != 0){
                rIndex++;
                if(reader[rIndex].toChar() == '[')
                    brackets++
                else if(reader[rIndex].toChar() == ']')
                    brackets--;
            }
        }
    }

    fun endCycle(){
        if(bytes[index] != 0.toByte()) {
            var brackets = 1;
            while(brackets != 0){
                rIndex--;
                if(reader[rIndex].toChar() == '[')
                    brackets--;
                else if(reader[rIndex].toChar() == ']')
                    brackets++;
            }
        }
    }

    fun doCommand(c: Char) {
        when(c) {
            '>' -> indInc();
            '<' -> indDec();
            '+' -> bytes[index]++;
            '-' -> bytes[index]--;
            '.' -> { str[charIndex] = bytes[index].toChar();
                charIndex++}
            ',' -> { val m = readLine().toString(); bytes[index] = (m.get(0).toByte() - '0'.toByte()).toByte()}
            '[' -> startCycle();
            ']' -> endCycle();
            13.toChar(),9.toChar(),32.toChar() -> {} //Ignore spaces, tabs and enters
            else -> System.err.println("Invalid Syntax");
        }
    }

    fun start(){
        while(rIndex < reader.size){
            doCommand(reader[rIndex].toChar());
            rIndex++;
        }
        if(charIndex > 1) //Crunch
            str = str.copyOfRange(0,charIndex-1);
        print(str);
    }
}