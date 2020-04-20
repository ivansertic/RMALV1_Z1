

class Yahtzee(private val numberOfDice: Int = 6,
              private val diceToRoll:MutableList<Die> = mutableListOf(),
              private val lockedDice:MutableList<Die> = mutableListOf()) {



    init {
        populateDiceToRoll(diceToRoll)
    }

    fun rollDice(){
        this.diceToRoll.forEach {die:Die ->
            die.rollDie()
        }
    }

    fun showDiceToRoll(){
        this.diceToRoll.forEach{die:Die ->
            println("${diceToRoll.indexOf(die)+1}: ${die.currentValue}")
        }
    }

    fun lockDie(index: Int): Boolean{
        var position = if(index <= this.diceToRoll.size && index > 0) index else return false
        when(position) {
            1 ->
                moveDie(index)
            2->
                moveDie(index)
            3->
                moveDie(index)
            4->
                moveDie(index)
            5->
                moveDie(index)
            6->
                moveDie(index)
            else->
                return  false
        }

        return true
    }

    fun showLockedDice(){
        this.lockedDice.forEach{die:Die ->
            println("${lockedDice.indexOf(die)+1}: ${die.currentValue}")
        }
    }

    fun checkForPoker():Boolean{
        var allThrows: MutableList<Die> = mutableListOf()
        var counter: Int = 0
        var hit: Boolean = false

        makeCheckList(allThrows)

        allThrows.forEach { die:Die ->
            allThrows.forEach {die2:Die->
                if(die.currentValue == die2.currentValue){
                    counter++
                }
            }
            if(counter == 4){
                hit = true
                return hit
            }
            counter = 0
        }

        return hit
    }

    fun checkForYahtzee():Boolean{
        var allThrows: MutableList<Die> = mutableListOf()
        var counter: Int = 0
        var hit: Boolean = false

        makeCheckList(allThrows)

        allThrows.forEach { die:Die ->
            allThrows.forEach {die2:Die->
                if(die.currentValue == die2.currentValue){
                    counter++
                }
            }
            if(counter == 5){
                hit = true
                return hit
            }
            counter = 0
        }

        return hit
    }

    fun checkForScale(): Boolean{
        var allThrows: MutableList<Die> = mutableListOf()
        var counter: Int = 0
        var hit: Boolean = true

        makeCheckList(allThrows)
        allThrows.sortBy { it.currentValue }

        val listWithoutDuplicates: List<Die> = allThrows.distinctBy { it.currentValue }

        if(listWithoutDuplicates.size < 5)
        {
            return false
        }

        var i = 0

        for(i in i..3){

            if(listWithoutDuplicates[i].currentValue - listWithoutDuplicates[i+1].currentValue != -1) {
                hit = false
                break
            }
        }

        return hit
    }

    fun checkAvailableDice():Int{
        return this.diceToRoll.size
    }

    private fun populateDiceToRoll(dieList:MutableList<Die>){
        for(i in 1..numberOfDice){
            dieList.add(Die())
        }
    }

    private fun moveDie(index:Int){
        this.lockedDice.add(this.diceToRoll[index - 1])
        this.diceToRoll.removeAt(index-1)
    }

    private fun makeCheckList(allThrows:MutableList<Die>){
        this.diceToRoll.forEach { die:Die ->
            allThrows.add(die)
        }

        this.lockedDice.forEach { die:Die ->
            allThrows.add(die)
        }
    }

}