import java.io.File
import java.io.Reader
//Vm like VirtualMachine. Too pretentious
class Vm (private val fileName: String,private val reader : Reader) {

    private var bytes = ByteArray(30000,{i -> 0}); //Vm space
    private var index = 0; //index of chosen Byte

    private var codeText = File(fileName).readBytes(); //Text of program
    private var rIndex = 0; //index of command in codeText

    var output = CharArray(100,{ i -> 0.toChar()}); // Output
    private var outputIndex = 0; //Output Index

    private fun indInc(){
        if (index + 1 >= 30000)
            index = 0;
        else
            index++;
    }

    private fun indDec(){
        if (index - 1 < 0)
            index = 29999;
        else
            index--;
    }

    private fun startCycle(){
        if(bytes[index] == 0.toByte()) {
            var brackets = 1;

            while(brackets != 0){

                rIndex++;

                if(codeText[rIndex].toChar() == '[')
                    brackets++
                else if(codeText[rIndex].toChar() == ']')
                    brackets--;

                if(rIndex == codeText.size) {
                    System.err.println("Invalid Loop");
                    return;
                }

            }

        }
    }

    private fun endCycle(){
        if(bytes[index] != 0.toByte()) {
            var brackets = 1;

            while(brackets != 0){
                rIndex--;

                if(codeText[rIndex].toChar() == '[')
                    brackets--;
                else if(codeText[rIndex].toChar() == ']')
                    brackets++;

                if(rIndex == codeText.size) {
                    System.err.println("Invalid Loop");
                    return;
                }

            }
        }
    }

    private fun readNumber(){
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
            rIndex = codeText.size;
            return;
        }
        val m = Integer.parseInt(String(buff.copyOfRange(0,endOfBuff)));
        bytes[index] = m.toByte();
    }

    private fun doCommand(c: Char) {

        when(c) {
            '>' -> indInc();
            '<' -> indDec();
            '+' -> bytes[index]++;
            '-' -> bytes[index]--;
            '.' -> {output[outputIndex] = bytes[index].toChar(); outputIndex++}
            ',' -> readNumber();
            '[' -> startCycle();
            ']' -> endCycle();
            13.toChar(),9.toChar(),32.toChar() -> {} //Ignore spaces, tabs and enters
            else -> System.err.println("Invalid Syntax");
        }
    }

    private fun init(){
        output = CharArray(100,{ i -> 0.toChar()});
        outputIndex = 0;
        bytes = ByteArray(30000,{i -> 0});
        index = 0;
        codeText = File(fileName).readBytes();
        rIndex = 0;
    }

    fun start(){
        init();
        while(rIndex < codeText.size){
            doCommand(codeText[rIndex].toChar());
            rIndex++;
        }
        output = output.copyOfRange(0, outputIndex);
        print(output);
    }
}