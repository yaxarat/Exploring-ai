package kohonen

import java.util.ArrayList

object Utility {

    fun parse(line: String): ArrayList<Double> {
        val nums: List<String> = line.split(",")
        val x1: Double = nums[0].toDouble()
        val x2: Double = nums[1].toDouble()
        val normalized = normalize(x1, x2)
        val out: ArrayList<Double> = ArrayList()

        out.add(normalized.first)
        out.add(normalized.second)
        return out
    }

    fun normalize(x1: Double, x2: Double): Pair<Double, Double> {
        val factor = Math.sqrt(x1 * x1 + x2 * x2)
        return Pair(x1 / factor, x2 / factor)
    }

    fun euclideanDistance(arg1: ArrayList<Double>, arg2: DoubleArray): Double {
        var sum = 0.0
        for (i in arg1.indices) {
            sum += (arg1[i] - arg2[i]) * (arg1[i] - arg2[i])
        }
        return Math.sqrt(sum)
    }

    fun findNearest(weight: ArrayList<Double>, neurons: ArrayList<DoubleArray>): Int {
        var distance = Utility.euclideanDistance(weight, neurons[0])
        var nearest = 0

        for (index in 1 until neurons.size) {
            val newDistance = Utility.euclideanDistance(weight, neurons[index])

            if (distance > newDistance) {
                distance = newDistance
                nearest = index
            }
        }
        return nearest
    }

    // Use for data normalization
    fun normalizeAndExportData() {
        Kohonen_Normalized.import()
        Kohonen_Normalized.export()
    }
}
