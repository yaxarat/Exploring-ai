package kohonen

import java.io.File
import kotlin.random.Random

object Kohonen_Normalized {
    // Constant variables
    private const val path = "/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex1_data.txt"
    private const val learnRate: Double = 0.25
    private val neurons: ArrayList<DoubleArray> = ArrayList()
    private val data: ArrayList<ArrayList<Double>> = ArrayList()
    private const val iteration: Int = 120

    fun import() {
        File(path).forEachLine {
            data.add(Utility.parse(it))
        }
    }

    fun export() {
        File("/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex1_normalized.txt").printWriter().use { out ->
            data.forEach {
                out.println("${it[0]},${it[1]}")
            }
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
                val newNeuron = DoubleArray(2)

                for (index in neuron.indices) {
                    neuron[index] += learnRate * (weight[index] - neuron[index])
                }

                newNeuron[0] = Utility.normalize(neuron[0], neuron[1]).first
                newNeuron[1] = Utility.normalize(neuron[0], neuron[1]).second
                neurons[nearest] = newNeuron
            }
        }
    }

    fun neuron2() {
        // Initial neuron weights based on normalized data
        val n1 = Utility.normalize(-0.9, -0.4)
        val n2 = Utility.normalize(0.8, 0.4)

        neurons.add(doubleArrayOf(n1.first, n1.second))
        neurons.add(doubleArrayOf(n2.first, n2.second))

        kohonenAlgo()
        printer("For 2 neurons:")
    }

    fun neuron3() {
        // Initial neuron weights based on normalized data
        val n1 = Utility.normalize(-0.9, -0.4)
        val n2 = Utility.normalize(0.8, 0.4)
        val n3 = Utility.normalize(1.0, 0.1)

        neurons.add(doubleArrayOf(n1.first, n1.second))
        neurons.add(doubleArrayOf(n2.first, n2.second))
        neurons.add(doubleArrayOf(n3.first, n3.second))

        kohonenAlgo()
        printer("For 3 neurons:")
    }

    fun neuron7() {
        // Initial neuron weights based on normalized data
        val n1 = Utility.normalize(-0.9, -0.4)
        val n2 = Utility.normalize(0.8, 0.4)
        val n3 = Utility.normalize(1.0, 1.0)
        val n4 = Utility.normalize(0.75, 0.5)
        val n5 = Utility.normalize(-0.85, -0.36)
        val n6 = Utility.normalize(0.95, 0.2)
        val n7 = Utility.normalize(-0.75, -0.42)

        neurons.add(doubleArrayOf(n1.first, n1.second))
        neurons.add(doubleArrayOf(n2.first, n2.second))
        neurons.add(doubleArrayOf(n3.first, n3.second))
        neurons.add(doubleArrayOf(n4.first, n4.second))
        neurons.add(doubleArrayOf(n5.first, n5.second))
        neurons.add(doubleArrayOf(n6.first, n6.second))
        neurons.add(doubleArrayOf(n7.first, n7.second))

        kohonenAlgo()
        printer("For 7 neurons:")
    }

    fun random(numNeuron: Int) {
        for (i in 0 until numNeuron) {
            val x1: Double = Random.nextDouble(-2.0, 2.1)
            val x2: Double = Random.nextDouble(-2.0, 2.1)
            val n = Utility.normalize(x1, x2)
            neurons.add(doubleArrayOf(n.first, n.second))
        }

        kohonenAlgo()
        printer("Random - For $numNeuron neurons:")
    }
}

fun main() {
    // Utility.normalizeAndExportData()
    Kohonen_Normalized.import()
    Kohonen_Normalized.neuron2()
    Kohonen_Normalized.neuron3()
    Kohonen_Normalized.neuron7()
    Kohonen_Normalized.random(2)
    Kohonen_Normalized.random(3)
    Kohonen_Normalized.random(7)
}
