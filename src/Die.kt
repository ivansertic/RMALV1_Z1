class Die(private val minValue: Int = 1, private val maxValue: Int = 6) {
    var currentValue: Int
        //private set

    init {
        currentValue = 6
    }

    fun rollDie(){
        this.currentValue = (minValue..maxValue).random()
    }
}