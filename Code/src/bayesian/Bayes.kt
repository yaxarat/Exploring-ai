package bayesian

import java.io.File

object Bayes {
    private val trainingData: ArrayList<Entry> = ArrayList()
    private val testData: ArrayList<Entry> = ArrayList()
    private val c1: ArrayList<Entry> = ArrayList()
    private val c2: ArrayList<Entry> = ArrayList()
    private val c3: ArrayList<Entry> = ArrayList()
    private var truePositive = 0
    private lateinit var cl1: Class
    private lateinit var cl2: Class
    private lateinit var cl3: Class
    private const val trainingPath = "/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex2_train.txt"
    private const val testPath = "/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex2_test.txt"

    private fun parse(input: String): Pair<Double, Int> {
        val split = input.split(",")
        return Pair(split[0].toDouble(),split[1].toInt())
    }

    fun import() {
        File(trainingPath).forEachLine {
            trainingData.add(Entry(parse(it).first, parse(it).second))
        }
        File(testPath).forEachLine {
            testData.add(Entry(parse(it).first, parse(it).second))
        }
    }

    fun test() {
        for (entry in testData) {
            when {
                entry.x1 <= separator(cl1, cl2) -> {
                    if (entry.label == 1) {
                        truePositive++
                    }
                }
                entry.x1 <= separator(cl2, cl3) -> {
                    if (entry.label == 2) {
                        truePositive++
                    }
                }
                else -> {
                    if (entry.label == 3) {
                        truePositive++
                    }
                }
            }
        }
    }

    fun classify() {
        for (entry in trainingData) {
            when (entry.label) {
                1 -> c1.add(entry)
                2 -> c2.add(entry)
                3 -> c3.add(entry)
            }
        }
        cl1 = Class.create(c1, 1)
        cl2 = Class.create(c2, 2)
        cl3 = Class.create(c3, 3)
    }
    
    private fun separator(fClass: Class, sClass: Class): Double {
        var fMean = fClass.mean
        var sMean = sClass.mean
        var count = 0
        
        while (fMean < sMean) {
            fMean += fClass.stdv
            sMean -= sClass.stdv
            count++
        }
        return (fClass.mean + fClass.stdv * count + (sClass.mean - sClass.stdv * count)) / 2.0
    }

    fun report() {
        println("Train report -------------------------------------------")
        println("Total count for training dataset is ${trainingData.size}")
        println("Includes ${c1.size} of label ${cl1.label}")
        println("Includes ${c2.size} of label ${cl2.label}")
        println("Includes ${c3.size} of label ${cl3.label}")
        println("Class statistics: $cl1\n$cl2\n$cl3")
        println("Probability of a randomly selected entry being in: ")
        println("Label 1: ${c1.size.toDouble() / trainingData.size}")
        println("Label 2: ${c2.size.toDouble() / trainingData.size}")
        println("Label 3: ${c3.size.toDouble() / trainingData.size}")
        println("Test report --------------------------------------------")
        println("Total count for test dataset is ${testData.size}")
        println("Number of correct classification is $truePositive")
        println("Correct percentage is ${truePositive.toDouble() / testData.size}")
    }
}

fun main() {
    Bayes.import()
    Bayes.classify()
    Bayes.test()
    Bayes.report()
}
