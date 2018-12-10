package kohonen

import java.io.File
import java.util.ArrayList
import kotlin.random.Random

object Kohonen_Original {
    // Constant variables
    private const val path = "/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex1_data.txt"
    private const val learnRate: Double = 0.25
    private val neurons: ArrayList<DoubleArray> = ArrayList()
    private val data: ArrayList<ArrayList<Double>> = ArrayList()
    private const val iteration: Int = 120

    fun import() {
        File(path).forEachLine {
            val nums: List<String> = it.split(",")
            val x1: Double = nums[0].toDouble()
            val x2: Double = nums[1].toDouble()
            val out: ArrayList<Double> = java.util.ArrayList()
            out.add(x1)
            out.add(x2)
            data.add(out)
        }
    }

    private fun printer(header: String) {
        println(header)
        for (index in neurons.indices) {
            println(neurons[index][0].toString() + ", " + neurons[index][1])
        }
        clear()
    }

    private fun clear() {
        neurons.clear()
    }

    private fun kohonenAlgo() {
        for (i in 0 until iteration) {
            for (weight in data) {
                val nearest = Utility.findNearest(weight, neurons)
                val neuron = neurons[nearest]

                for (index in neuron.indices) {
                    neuron[index] += learnRate * (weight[index] - neuron[index])
                }
            }
        }
    }

    fun neuron2() {
        // Initial neuron weights based on normalized data
        val n1 = doubleArrayOf(-2.5, -1.0)
        val n2 = doubleArrayOf(0.43, 0.43)

        neurons.add(n1)
        neurons.add(n2)

        kohonenAlgo()
        printer("For 2 neurons:")
    }

    fun neuron3() {
        // Initial neuron weights based on normalized data
        val n1 = doubleArrayOf(-2.5, -1.0)
        val n2 = doubleArrayOf(0.43, 0.43)
        val n3 = doubleArrayOf(2.0, 1.0)

        neurons.add(n1)
        neurons.add(n2)
        neurons.add(n3)

        kohonenAlgo()
        printer("For 3 neurons:")
    }

    fun neuron7() {
        // Initial neuron weights based on normalized data
        val n1 = doubleArrayOf(-2.5, -1.0)
        val n2 = doubleArrayOf(0.43, 0.43)
        val n3 = doubleArrayOf(2.0, 1.0)
        val n4 = doubleArrayOf(-2.2, -1.0)
        val n5 = doubleArrayOf(0.2, 0.2)
        val n6 = doubleArrayOf(1.3, 0.8)
        val n7 = doubleArrayOf(1.9, 1.2)

        neurons.add(n1)
        neurons.add(n2)
        neurons.add(n3)
        neurons.add(n4)
        neurons.add(n5)
        neurons.add(n6)
        neurons.add(n7)

        kohonenAlgo()
        printer("For 7 neurons:")
    }

    fun random(numNeuron: Int) {
        for (i in 0 until numNeuron) {
            val x1: Double = Random.nextDouble(-2.0, 2.1)
            val x2: Double = Random.nextDouble(-2.0, 2.1)
            neurons.add(doubleArrayOf(x1, x2))
        }

        kohonenAlgo()
        printer("Random - For $numNeuron neurons:")
    }
}

fun main() {
    Kohonen_Original.import()
    Kohonen_Original.neuron2()
    Kohonen_Original.neuron3()
    Kohonen_Original.neuron7()
    Kohonen_Normalized.random(2)
    Kohonen_Normalized.random(3)
    Kohonen_Normalized.random(7)
}
