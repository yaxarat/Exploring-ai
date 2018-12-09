package kohonen

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

    fun euclidDistance(v1: ArrayList<Double>, v2: DoubleArray): Double {
        var sum = 0.0

        for (i in v1.indices) {
            sum += (v1[i] - v2[i]) * (v1[i] - v2[i])
        }

        return Math.sqrt(sum)
    }

}
