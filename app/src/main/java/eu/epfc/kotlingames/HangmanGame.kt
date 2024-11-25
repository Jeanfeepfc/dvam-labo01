package eu.epfc.kotlingames
import java.lang.Thread.sleep
import java.io.File
import kotlin.random.Random

fun main() {
    println("game start")
    for(i in 0..2) {
        println("Start in ${3 - i} second(s)")
        sleep(200)
    }
    println("game starts")

    val wordToGuest = generateWordToGuess()
    val lettersPlayedByUser:  MutableList<Char> = mutableListOf()
    var victory = false
    val maxTurn = 8
    for( turn in 1..maxTurn) {
        println("Type a letter:")
        val stringInput = readLine()
        if (stringInput != null) {
            lettersPlayedByUser.add(stringInput.lowercase()[0])
            val maskedWord = getMaskedWordToGuess(wordToGuest, lettersPlayedByUser)
            println(maskedWord)
            if(maskedWord == wordToGuest) {
                victory = true
                break
            } else {
                println("more ${maxTurn - turn} try")
            }
        }
    }

    if (victory ) {
        println("YOU WIN")
    } else {
        println("GAME OVER.")
        println("the word was $wordToGuest")
    }
}

fun generateWordToGuess() : String {
    val dictionaryFile = File("dictionary.txt")
    val dictionary = dictionaryFile.readLines()
    val randomIndex = Random.nextInt(dictionary.count() - 1)

    return dictionary[randomIndex].lowercase()
}

fun getMaskedWordToGuess( wordToGuest: String, playingLetters: List<Char>) :String {
    var maskedWord: String = ""

    for (c in wordToGuest) {
        if (playingLetters.contains(c)) {
            maskedWord += c
        } else {
            maskedWord+= "*"
        }
    }
    return  maskedWord
}