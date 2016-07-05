import java.io.File
import java.io.Reader
//Vm like VirtualMachine. Too pretentious
class Vm (private val fileName: String,val reader : Reader) {
    private var bytes = ByteArray(30000,{i -> 0}); //Vm space
    private var index = 0; //index of chosen Byte
    private var rIndex = 0; //index of command in Reader
    private var code = File(fileName).readBytes(); //Text of program
    var str = CharArray(100,{i -> 0.toChar()}); // Output
    var charIndex = 0; //Output Index

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

                if(code[rIndex].toChar() == '[')
                    brackets++
                else if(code[rIndex].toChar() == ']')
                    brackets--;

                if(rIndex == code.size) {
                    System.err.println("Invalid Loop");
                    return;
                }

            }

        }
    }

    fun endCycle(){
        if(bytes[index] != 0.toByte()) {
            var brackets = 1;

            while(brackets != 0){
                rIndex--;

                if(code[rIndex].toChar() == '[')
                    brackets--;
                else if(code[rIndex].toChar() == ']')
                    brackets++;

                if(rIndex == code.size) {
                    System.err.println("Invalid Loop");
                    return;
                }

            }
        }
    }

    fun read(){
        val buff = CharArray(4,{i -> 0.toChar()});
        var buffChar = reader.read();
        var endOfBuff = 0;

        while((buffChar != -1) && (buffChar.toChar() != '\n') && (buffChar.toChar() != ' ') && (endOfBuff < 4)) {
            buff[endOfBuff] = buffChar.toChar();
            buffChar = reader.read();
            endOfBuff++;
        }

        if(endOfBuff == 0 || endOfBuff == 4) {
            System.err.println("Invalid Input");
            rIndex = code.size;
            return;
        }
        val m = Integer.parseInt(String(buff.copyOfRange(0,endOfBuff)));
        bytes[index] = m.toByte();
    }

    fun doCommand(c: Char) {

        when(c) {
            '>' -> indInc();
            '<' -> indDec();
            '+' -> bytes[index]++;
            '-' -> bytes[index]--;
            '.' -> { str[charIndex] = bytes[index].toChar(); charIndex++}
            ',' -> read();
            '[' -> startCycle();
            ']' -> endCycle();
            13.toChar(),9.toChar(),32.toChar() -> {} //Ignore spaces, tabs and enters
            else -> System.err.println("Invalid Syntax");
        }
    }

    fun start(){
        code = File(fileName).readBytes();
        while(rIndex < code.size){
            doCommand(code[rIndex].toChar());
            rIndex++;
        }

        str = str.copyOfRange(0,charIndex);
        print(str);
    }
}