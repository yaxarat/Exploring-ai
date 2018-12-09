package kohonen

import java.io.File
import java.util.*

object Neuron2 {
    // Constant variables
    private const val path = "/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex1_data.txt"
    private const val learningConstant: Double = 0.3
    private val neurons: ArrayList<DoubleArray> = ArrayList()
    private val data: ArrayList<ArrayList<Double>> = ArrayList()
    private const val iteration: Int = 15

    fun import() {
        File(path).forEachLine {
            data.add(Utility.parse(it))
        }
    }

    private fun export(exportFile: ArrayList<ArrayList<Double>>) {
        File("/Users/yaxar/Desktop/AI_Final/Code/src/Data/Ex1_normalized.txt").printWriter().use { out ->
            exportFile.forEach {
                out.println("${it[0]},${it[1]}")
            }
        }
    }

    fun kohonen() {
        // Initial neuron weights based on normalized data
        val n1 = Utility.normalize(-0.9, -0.4)
        val n2 = Utility.normalize(0.8, 0.4)

        neurons.add(doubleArrayOf(n1.first, n1.second))
        neurons.add(doubleArrayOf(n2.first, n2.second))

        for (i in 0 until iteration) {
            for (weight in data) {
                val nearest = findNearest(weight)
                val neuron = neurons[nearest]
                val newNeuron = DoubleArray(2)

                for (index in neuron.indices) {
                    neuron[index] += learningConstant * (weight[index] - neuron[index])
                }

                newNeuron[0] = Utility.normalize(neuron[0], neuron[1]).first
                newNeuron[1] = Utility.normalize(neuron[0], neuron[1]).second
                neurons[nearest] = newNeuron
            }
        }

        println(neurons[0][0].toString() + ", " + neurons[0][1])
        println(neurons[1][0].toString() + ", " + neurons[1][1])
    }

    private fun findNearest(arg: ArrayList<Double>): Int {
        return if (Utility.euclidDistance(arg, neurons[0]) > Utility.euclidDistance(arg, neurons[1])) {
            1
        } else 0
    }

    fun normalizeData() {
        import()
        export(data)
    }
}

fun main() {
    //Neuron2.normalizeData()
    Neuron2.import()
    Neuron2.kohonen()
}
