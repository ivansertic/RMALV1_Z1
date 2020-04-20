import java.lang.NumberFormatException

fun printOptions(){
    println("______OPTIONS_______")
    println("PRESS:")
    println("1: Re-roll All")
    println("2: Lock x Dice")
    println("3: Lock all and check score")
}

fun applyOption(): Int{
    var state = false
    var returnNumber: Int = 0
    do {
        val option:String = readLine()!!
        try {
            val parsedInt = option.toInt()
            if(parsedInt < 1 || parsedInt >3)
            {
                println("No such option")
            }else{
                returnNumber = parsedInt
                state = true
            }
        }catch (e: NumberFormatException){
            println("This is not a number")
        }
    }while(!state)
    return returnNumber
}


fun main() {

    val yahtzee = Yahtzee()
    val option :String?
    var play =true

    println("___________LETS PLAY YAHTZEE__________")
    println("Press: Y to Play")
    println("Press anything else to Quit")
    option = readLine()

    if(option?.toUpperCase() == "Y"){

        do {
            var throwNumber = 1
            println("_______FIRST THROW______")
            yahtzee.rollDice()
            println("_______FIRST THROW RESULTS______")
            yahtzee.showDiceToRoll()

            do {
                printOptions()
                var selectedOption = applyOption()
                when (selectedOption) {
                    1 -> {
                        println("______NEXT THROW________")
                        println("______LOCKED DICE_______")
                        yahtzee.showLockedDice()
                        println("______THROW RESULTS________")
                        yahtzee.rollDice()
                        yahtzee.showDiceToRoll()
                        throwNumber++
                    }

                    2 -> {
                        println("______ HOW MANY DICES DO YOU WANT TO LOCK________")
                        var state = false
                        var numberOfDie = 0
                        do {
                            val option2: String = readLine()!!
                            try {
                                val parsedInt = option2.toInt()
                                if (parsedInt < 1 || parsedInt > yahtzee.checkAvailableDice()) {
                                    println("Too many or few dice picked")
                                } else {
                                    numberOfDie = parsedInt
                                    state = true
                                }
                            } catch (e: NumberFormatException) {
                                println("This is not a number")
                            }
                        } while (!state)

                        println("_______SELECT DIE POSITION_______")
                        for (i in 0..numberOfDie - 1) {
                            var dieNumber = readLine()!!.toInt()
                            yahtzee.lockDie(dieNumber)
                            println("_______REST OF DICE TO LOCK________")
                            yahtzee.showDiceToRoll()
                        }

                        println("______NEXT THROW________")
                        println("______LOCKED DICE_______")
                        yahtzee.showLockedDice()
                        println("______THROW RESULTS________")
                        yahtzee.rollDice()
                        yahtzee.showDiceToRoll()

                        throwNumber++
                    }

                    3 -> {
                        if (yahtzee.checkForPoker()) {
                            println("You got poker")
                        }

                        if (yahtzee.checkForScale()) {
                            println("You got scale")
                        }

                        if (yahtzee.checkForYahtzee()) {
                            println("Yahtzee!!!")
                        }

                        play = false
                    }

                }
            }while (throwNumber < 3 && play)
            if(throwNumber == 3){
                if (yahtzee.checkForPoker()) {
                    println("You got poker")
                }

                if (yahtzee.checkForScale()) {
                    println("You got scale")
                }

                if (yahtzee.checkForYahtzee()) {
                    println("Yahtzee!!!")
                }

                play = false
            }
        }while(play)

    }

    println("Goodbye")


}