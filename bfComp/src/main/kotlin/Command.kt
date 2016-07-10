
class Command(val type: CommandType) {
    var value = 0;
    constructor(type: CommandType, value: Int) : this(type) {
        this.value = value;
    }
}