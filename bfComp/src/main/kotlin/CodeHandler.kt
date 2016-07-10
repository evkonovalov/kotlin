import java.io.File
import java.util.*

class CodeHandler(val fileName: String) {
    private val source = File(fileName).readBytes();
    private var index = 0;
    var commands = ArrayList<Command>();

    private var braceNow = 0;
    private var exitCode = 0;


    fun handleShift(){
        var value = 0;
        while((source[index].toChar() == '>' || source[index].toChar() == '<') && index < source.size) {
            if (source[index].toChar() == '>')
                value++;
            else
                value--;
            index++;
        }
        commands.add(Command(CommandType.SHIFT,value));
    }

    fun handleSum(){
        var value = 0;

        while((source[index].toChar() == '+' || source[index].toChar() == '-')) {
            if (source[index].toChar() == '+')
                value++;
            else
                value--;
            index++;
            if(index >= source.size)
                break;
        }
        commands.add(Command(CommandType.SUM,value));
    }

    fun handleCommand(c:Char){
        when(c) {
            '>','<' -> handleShift();
            '+','-' -> handleSum();
            '.' -> {commands.add(Command(CommandType.PRINT)); index++;}
            ',' -> {commands.add(Command(CommandType.READ)); index++;}
            '[' -> handleZero();
            ']' -> {braceNow--; commands.add(Command(CommandType.FINISH)); index++}
            13.toChar(),9.toChar(),32.toChar() -> {} //Ignore spaces, tabs and enters
            else -> {System.err.println("Invalid Syntax"); index = source.size; exitCode = -1}
        }
    }

    fun handleZero(){
        if(source[index].toChar() == '[' && source[index+2].toChar() == ']' &&
                (source[index+1].toChar() == '+' || source[index+1].toChar() == '-')){
            commands.add(Command(CommandType.ZERO));
            index+=3;
        } else {
            commands.add(Command(CommandType.START));
            braceNow++;
            index++;
        }
    }

    fun start(): Int{
        while(index < source.size){
            handleCommand(source[index].toChar());
        }
        if(braceNow != 0) {
            System.err.println("Invalid Loop");
            exitCode = -1;
        }
        return exitCode;
    }
}